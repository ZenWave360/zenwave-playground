package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PatchMapperTest {

    private TestUser user;
    private TestPatient patient;

    @BeforeEach
    void setUp() {
        user = new TestUser();
        user.setName("John");
        user.setEmail("john@example.com");
        user.setAge(30);

        patient = new TestPatient();
        patient.setName("Jane");
        patient.setGeneralInfo(new TestGeneralInfo());
        patient.getGeneralInfo().setFirstName("Jane");
        patient.getGeneralInfo().setLastName("Doe");

        patient.setAddresses(new ArrayList<>());
        patient.getAddresses().add(new TestAddress("123 Main St", "Springfield"));
        patient.getAddresses().add(new TestAddress("456 Oak Ave", "Shelbyville"));
        patient.getAddresses().add(new TestAddress("789 Pine Rd", "Capital City"));
    }

    @Test
    void testSimplePropertyUpdate() {
        Map<String, Object> updates = Map.of(
            "name", "Johnny",
            "age", 31
        );

        PatchMapper.patch(user, updates);

        assertEquals("Johnny", user.getName());
        assertEquals(31, user.getAge());
        assertEquals("john@example.com", user.getEmail()); // unchanged
    }

    @Test
    void testNestedPropertyUpdate() {
        Map<String, Object> updates = Map.of(
            "generalInfo.firstName", "Janet",
            "generalInfo.lastName", "Smith"
        );

        PatchMapper.patch(patient, updates);

        assertEquals("Janet", patient.getGeneralInfo().getFirstName());
        assertEquals("Smith", patient.getGeneralInfo().getLastName());
        assertEquals("Jane", patient.getName()); // unchanged
    }

    @Test
    void testCollectionElementUpdate() {
        Map<String, Object> updates = Map.of(
            "addresses[0].street", "999 New Street",
            "addresses[1].city", "New City"
        );

        PatchMapper.patch(patient, updates);

        assertEquals("999 New Street", patient.getAddresses().get(0).getStreet());
        assertEquals("Springfield", patient.getAddresses().get(0).getCity()); // unchanged
        assertEquals("456 Oak Ave", patient.getAddresses().get(1).getStreet()); // unchanged
        assertEquals("New City", patient.getAddresses().get(1).getCity());
    }

    @Test
    void testCollectionAutoExpansion() {
        Map<String, Object> updates = Map.of(
            "addresses[5].street", "Auto Expanded Street",
            "addresses[5].city", "Auto City"
        );

        PatchMapper.patch(patient, updates);

        assertEquals(6, patient.getAddresses().size());
        assertNull(patient.getAddresses().get(3)); // auto-filled with null
        assertNull(patient.getAddresses().get(4)); // auto-filled with null
        assertEquals("Auto Expanded Street", patient.getAddresses().get(5).getStreet());
        assertEquals("Auto City", patient.getAddresses().get(5).getCity());
    }

    @Test
    void testDeleteCollectionElement() {
        assertEquals(3, patient.getAddresses().size());

        Map<String, Object> updates = new HashMap<>();
        updates.put("addresses[1]", null);

        PatchMapper.patch(patient, updates);

        assertEquals(2, patient.getAddresses().size());
        assertEquals("123 Main St", patient.getAddresses().get(0).getStreet());
        assertEquals("789 Pine Rd", patient.getAddresses().get(1).getStreet()); // shifted down
    }

    @Test
    void testClearEntireCollection() {
        assertEquals(3, patient.getAddresses().size());

        Map<String, Object> updates = new HashMap<>();
        updates.put("addresses", null);

        PatchMapper.patch(patient, updates);

        assertNull(patient.getAddresses());
    }

    @Test
    void testClearEntireCollectionAndThenAdd() {
        assertEquals(3, patient.getAddresses().size());

        Map<String, Object> updates = new HashMap<>();
        updates.put("addresses", null);
        updates.put("addresses[0].street", "New Street");

        PatchMapper.patch(patient, updates);

        assertNotNull(patient.getAddresses());
        assertEquals(1, patient.getAddresses().size());
        assertEquals("New Street", patient.getAddresses().get(0).getStreet());
    }


    @Test
    void testMixedOperations() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Updated Jane");
        updates.put("generalInfo.firstName", "Updated Janet");
        updates.put("addresses[0].street", "Updated Street");
        updates.put("addresses[10].street", "Far Away Street"); // auto-expand
        updates.put("addresses[2]", null); // delete element

        PatchMapper.patch(patient, updates);

        assertEquals("Updated Jane", patient.getName());
        assertEquals("Updated Janet", patient.getGeneralInfo().getFirstName());
        assertEquals("Updated Street", patient.getAddresses().get(0).getStreet());
        assertEquals(10, patient.getAddresses().size()); // one deleted, but auto-expansion happens first
        // Note: auto-expansion happens in first pass, deletion in second pass
    }

    @Test
    void testTwoPassBehavior() {
        // This test verifies that non-null values are processed before null values
        Map<String, Object> updates = new LinkedHashMap<>(); // preserve order
        updates.put("addresses[3].street", "New Address"); // auto-expand first
        updates.put("addresses[1]", null); // then delete

        PatchMapper.patch(patient, updates);

        assertEquals(3, patient.getAddresses().size()); // 3 original + 1 new - 1 deleted
        assertEquals("New Address", patient.getAddresses().get(2).getStreet()); // index shifted after deletion
    }

    @Test
    void testNullInputHandling() {
        TestUser originalUser = new TestUser();
        originalUser.setName("Original");

        // Null target
        TestUser result1 = PatchMapper.patch(null, Map.of("name", "New"));
        assertNull(result1);

        // Null input
        TestUser result2 = PatchMapper.patch(originalUser, null);
        assertEquals("Original", result2.getName());
        assertSame(originalUser, result2);
    }

    @Test
    void testInvalidPathHandling() {
        // Should not throw exceptions, just log warnings
        Map<String, Object> updates = Map.of(
            "nonExistentField", "value",
            "generalInfo.nonExistentNestedField", "value",
            "addresses[100].street", "value" // out of bounds but should auto-expand
        );

        assertDoesNotThrow(() -> PatchMapper.patch(patient, updates));

        // Auto-expansion should still work
        assertEquals(101, patient.getAddresses().size());
        assertEquals("value", patient.getAddresses().get(100).getStreet());
    }

    @Test
    void testDeleteMultipleElements() {
        assertEquals(3, patient.getAddresses().size());

        Map<String, Object> updates = new HashMap<>();
        updates.put("addresses[0]", null);
        updates.put("addresses[2]", null);

        PatchMapper.patch(patient, updates);

        assertEquals(1, patient.getAddresses().size());
        assertEquals("456 Oak Ave", patient.getAddresses().get(0).getStreet()); // middle element remains
    }

    @Test
    void testDeleteAndUpdateSameCollection() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("addresses[0].street", "Updated Street");
        updates.put("addresses[1]", null); // delete element
        updates.put("addresses[2].city", "Updated City");

        PatchMapper.patch(patient, updates);

        assertEquals(2, patient.getAddresses().size());
        assertEquals("Updated Street", patient.getAddresses().get(0).getStreet());
        assertEquals("Updated City", patient.getAddresses().get(1).getCity()); // was index 2, now 1
    }

    @Test
    void testNestedCollectionDeletion() {
        // Create nested structure for testing
        TestPatient nestedPatient = new TestPatient();
        nestedPatient.setName("Nested Test");
        nestedPatient.setAddresses(new ArrayList<>());
        nestedPatient.getAddresses().add(new TestAddress("Street 1", "City 1"));
        nestedPatient.getAddresses().add(new TestAddress("Street 2", "City 2"));

        Map<String, Object> updates = new HashMap<>();
        updates.put("addresses[0]", null);

        PatchMapper.patch(nestedPatient, updates);

        assertEquals(1, nestedPatient.getAddresses().size());
        assertEquals("Street 2", nestedPatient.getAddresses().get(0).getStreet());
    }

    // Test helper classes
    static class TestUser {
        private String name;
        private String email;
        private Integer age;

        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
    }

    static class TestPatient {
        private String name;
        private TestGeneralInfo generalInfo;
        private List<TestAddress> addresses;

        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public TestGeneralInfo getGeneralInfo() { return generalInfo; }
        public void setGeneralInfo(TestGeneralInfo generalInfo) { this.generalInfo = generalInfo; }
        public List<TestAddress> getAddresses() { return addresses; }
        public void setAddresses(List<TestAddress> addresses) { this.addresses = addresses; }
    }

    static class TestGeneralInfo {
        private String firstName;
        private String lastName;

        // getters and setters
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }

    static class TestAddress {
        private String street;
        private String city;

        public TestAddress() {}

        public TestAddress(String street, String city) {
            this.street = street;
            this.city = city;
        }

        // getters and setters
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
    }
}
