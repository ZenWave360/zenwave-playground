package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NotWritablePropertyException;

/**
 * Utility class for performing partial updates on Java objects using field-level patching.
 *
 * <p>This mapper allows you to update specific fields of an object by providing a map of property paths
 * and their new values. It supports simple properties, nested objects, and collection elements.</p>
 *
 * <h3>Supported Property Path Formats:</h3>
 * <ul>
 *   <li><strong>Simple properties:</strong> {@code "name", "email", "age"}</li>
 *   <li><strong>Nested properties:</strong> {@code "address.street", "user.profile.displayName"}</li>
 *   <li><strong>Collection elements:</strong> {@code "addresses[0]", "contacts[2].phoneNumber"}</li>
 *   <li><strong>Deep nested paths:</strong> {@code "patient.addresses[1].city", "order.items[0].product.name"}</li>
 * </ul>
 *
 * <h3>Collection Management:</h3>
 * <p>You can add, update, or delete collection elements using different approaches:</p>
 *
 * <ul>
 *   <li><strong>Update collection element:</strong> {@code "addresses[0].street" -> "New Street"}</li>
 *   <li><strong>Add to collection:</strong> {@code "addresses[5].street" -> "Another Street"} (auto-expands list)</li>
 *   <li><strong>Delete single element:</strong> {@code "addresses[1]" -> null} (removes element at index 1)</li>
 *   <li><strong>Clear entire collection:</strong> {@code "addresses" -> null} (removes all elements)</li>
 * </ul>
 *
 * <p><strong>Note:</strong> If you want to replace a complete collection or nested object, just set it to null first.</p>
 * <p><strong>Note:</strong> To remove a collection element, set the element to null but after populating the other elements in that collection, so the shifting of indexes is not an issue. Also remove elements from the end of the collection first.</p>
 *
 * <h3>Usage Examples:</h3>
 *
 * <h4>Basic Property Updates:</h4>
 * <pre>{@code
 * User user = new User();
 * Map<String, Object> updates = Map.of(
 *     "name", "John Doe",
 *     "email", "john@example.com",
 *     "age", 30
 * );
 * PatchMapper.patch(user, updates);
 * }</pre>
 *
 * <h4>Nested Object Updates:</h4>
 * <pre>{@code
 * Patient patient = new Patient();
 * Map<String, Object> updates = Map.of(
 *     "generalInfo.name", "Jane Smith",
 *     "generalInfo.birthDate", LocalDate.of(1990, 5, 15),
 *     "healthInsurance.provider", "Blue Cross"
 * );
 * PatchMapper.patch(patient, updates);
 * }</pre>
 *
 * <h4>Collection Updates:</h4>
 * <pre>{@code
 * Patient patient = new Patient();
 * Map<String, Object> updates = Map.of(
 *     // Update existing address
 *     "addresses[0].street", "123 Main St",
 *     "addresses[0].city", "Springfield",
 *
 *     // Add new contact (list auto-expands)
 *     "medicalContacts[2].name", "Dr. Johnson",
 *     "medicalContacts[2].phone", "555-0123",
 *
 *     // Delete specific element
 *     "addresses[1]", null,
 *
 *     // Clear all emergency contacts
 *     "emergencyContacts", null
 * );
 * PatchMapper.patch(patient, updates);
 * }</pre>
 *
 * <h3>Behavior Notes:</h3>
 * <ul>
 *   <li><strong>Null safety:</strong> Returns original object if target or input is null</li>
 *   <li><strong>Error resilience:</strong> Continues processing other properties if one fails</li>
 *   <li><strong>Auto-expansion:</strong> Lists automatically grow to accommodate new indices</li>
 *   <li><strong>Two-pass processing:</strong> Non-null values first, then null values for deletions</li>
 * </ul>
 *
 * @author ZenWave360
 * @since 1.0
 */
public class PatchMapper {

    private static Logger log = LoggerFactory.getLogger(PatchMapper.class);

    /**
     * Applies partial updates to a target object using property paths and values from an input map.
     *
     * <p>This method processes updates in two phases to ensure consistent behavior:</p>
     * <ol>
     *   <li><strong>Value Updates:</strong> All non-null values are applied to their target properties</li>
     *   <li><strong>Collection Operations:</strong> Null values trigger collection clearing or element deletion</li>
     * </ol>
     *
     * <p><strong>Collection Deletion:</strong> Use {@code "collectionName[index]" -> null} to delete a specific
     * element, or {@code "collectionName" -> null} to clear the entire collection.</p>
     *
     * @param <T> the type of the target object
     * @param target the object to update (if null, no updates are performed)
     * @param input map of property paths to new values (null values trigger deletions)
     * @return the updated target object (same instance, modified in place)
     *
     * @example
     * <pre>{@code
     * // Update patient information
     * Map<String, Object> patientUpdates = Map.of(
     *     "generalInfo.name", "Alice Johnson",
     *     "addresses[0].street", "456 Oak Avenue",
     *     "addresses[1]", null,        // Delete element at index 1
     *     "medicalContacts", null      // Clear all medical contacts
     * );
     *
     * Patient updatedPatient = PatchMapper.patch(existingPatient, patientUpdates);
     * }</pre>
     */
    public static <T> T patch(T target, Map<String, Object> input) {
        if (target == null || input == null) {
            return target;
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);

        // First pass: handle non-null values
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            String propertyPath = entry.getKey();
            Object value = entry.getValue();

            if (isCollectionRootElement(propertyPath)) {
                // skip collection root elements like `order.items[0]` which are meant to be deleted in the second pass
                continue;
            }
            if (isIndexed(propertyPath) && value != null) {
                // ensure collection element exists before setting nested property
                ensureCollectionElementExists(beanWrapper, propertyPath);
            }
            try {
                beanWrapper.setPropertyValue(propertyPath, value);
            } catch (NotWritablePropertyException e) {
                log.warn("Property not writable: {}", propertyPath, e);
            }
        }

        // Second pass: handle null values for collections and element deletions
        for (Map.Entry<String, Object> entry : input.entrySet()) {
            String propertyPath = entry.getKey();
            Object value = entry.getValue();

            if (isCollectionRootElement(propertyPath) && value == null) {
                int index = extractIndex(propertyPath);
                String prefix = extractPrefix(propertyPath);
                Object collection = beanWrapper.getPropertyValue(prefix);
                if (collection instanceof List && index >= 0 && index < ((List<?>) collection).size()) {
                    ((List<?>) collection).remove(index);
                }
            }
        }

        return target;
    }

    private static boolean isCollectionRootElement(String path) {
        return Pattern.matches(".+\\[\\d+\\]$", path);
    }

    private static boolean isIndexed(String path) {
        return Pattern.matches(".+\\[\\d+\\].+", path);
    }

    private static String extractPrefix(String path) {
        int start = path.lastIndexOf('[');
        if (start > 0) {
            return path.substring(0, start);
        }
        return path;
    }

    private static int extractIndex(String path) {
        Pattern pattern = Pattern.compile("\\[(\\d+)\\]$");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    private static void ensureCollectionElementExists(BeanWrapper beanWrapper, String propertyPath) {
        int lastBracket = propertyPath.lastIndexOf('[');
        if (lastBracket == -1) return;

        int closeBracket = propertyPath.indexOf(']', lastBracket);
        if (closeBracket == -1) return;

        String collectionPath = propertyPath.substring(0, lastBracket);
        int index = Integer.parseInt(propertyPath.substring(lastBracket + 1, closeBracket));

        try {
            Object collection = beanWrapper.getPropertyValue(collectionPath);

            // Initialize collection if it's null
            if (collection == null) {
                Class<?> collectionType = beanWrapper.getPropertyType(collectionPath);
                if (List.class.isAssignableFrom(collectionType)) {
                    collection = new ArrayList<>();
                    beanWrapper.setPropertyValue(collectionPath, collection);
                }
            }

            if (collection instanceof List) {
                List<Object> list = (List<Object>) collection;
                // Expand list to accommodate the index
                while (list.size() <= index) {
                    list.add(null);
                }
                // Create new instance if the element is null
                if (list.get(index) == null) {
                    Class<?> elementType = getCollectionElementType(beanWrapper, collectionPath);
                    if (elementType != null) {
                        try {
                            Object newElement =
                                    elementType.getDeclaredConstructor().newInstance();
                            list.set(index, newElement);
                        } catch (Exception e) {
                            log.warn(
                                    "Could not create instance of {}: {}", elementType.getSimpleName(), e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Could not ensure collection element exists for path {}: {}", propertyPath, e.getMessage());
        }
    }

    private static Class<?> getCollectionElementType(BeanWrapper beanWrapper, String collectionPath) {
        try {
            // Try to get the element type from the collection
            Object collection = beanWrapper.getPropertyValue(collectionPath);
            if (collection instanceof List) {
                List<?> list = (List<?>) collection;
                if (!list.isEmpty()) {
                    return list.get(0).getClass();
                }
            }
        } catch (Exception e) {
            log.warn("Could not get collection element type for path {}: {}", collectionPath, e.getMessage());
        }

        // If the collection is empty or not a List, try to infer the type from the property
        try {
            Class<?> collectionType = beanWrapper.getPropertyType(collectionPath);
            if (List.class.isAssignableFrom(collectionType)) {
                // Try to get the generic type of the List
                java.lang.reflect.Type genericType = beanWrapper
                        .getPropertyTypeDescriptor(collectionPath)
                        .getElementTypeDescriptor()
                        .getType();
                if (genericType instanceof Class<?>) {
                    return (Class<?>) genericType;
                }
            }
        } catch (Exception e) {
            log.warn("Could not infer collection element type for path {}: {}", collectionPath, e.getMessage());
        }

        // Fallback: return null if we couldn't determine the type
        return null;
    }
}
