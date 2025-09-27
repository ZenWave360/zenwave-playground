package io.zenwave360.example.clinicaltool.modules.masterdata;

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.mappers.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * REST controller for MasterDataApi.
 */
@RestController
@RequestMapping("/api")
public class MasterDataApiController implements MasterDataApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private MasterDataService masterDataService;

    @Autowired
    public MasterDataApiController setMasterDataService(MasterDataService masterDataService) {
        this.masterDataService = masterDataService;
        return this;
    }

    private MasterDataDTOsMapper mapper = MasterDataDTOsMapper.INSTANCE;

    public MasterDataApiController(MasterDataService masterDataService) {

        this.masterDataService = masterDataService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<MasterDataDTO> createMasterData(MasterDataDTO reqBody) {
        log.debug("REST request to createMasterData: {}", reqBody);
        var input = mapper.asMasterData(reqBody);
        var masterData = masterDataService.createMasterData(input);
        MasterDataDTO responseDTO = mapper.asMasterDataDTO(masterData);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<MasterDataPaginatedDTO> listMasterData(Integer page, Integer limit, List<String> sort) {
        log.debug("REST request to listMasterData: {}, {}, {}", page, limit, sort);
        var masterDataPage = masterDataService.listMasterData(pageOf(page, limit, sort));
        var responseDTO = mapper.asMasterDataPaginatedDTO(masterDataPage);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<MasterDataDTO> getMasterData(Long id) {
        log.debug("REST request to getMasterData: {}", id);
        var masterData = masterDataService.getMasterData(id);
        if (masterData.isPresent()) {
            MasterDataDTO responseDTO = mapper.asMasterDataDTO(masterData.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<MasterDataDTO> updateMasterData(Long id, MasterDataDTO reqBody) {
        log.debug("REST request to updateMasterData: {}, {}", id, reqBody);
        var input = mapper.asMasterData(reqBody);
        var masterData = masterDataService.updateMasterData(id, input);
        if (masterData.isPresent()) {
            MasterDataDTO responseDTO = mapper.asMasterDataDTO(masterData.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteMasterData(Long id) {
        log.debug("REST request to deleteMasterData: {}", id);
        masterDataService.deleteMasterData(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<List<MasterDataKeyValueDTO>> listMasterDataOfType(String type, String lang) {
        log.debug("REST request to listMasterDataOfType: {}, {}", type, lang);
        var masterDataKeyValue = masterDataService.listMasterDataOfType(MasterDataType.valueOf(type), lang);
        var responseDTO = mapper.asMasterDataKeyValueDTOList(masterDataKeyValue);
        return ResponseEntity.status(200).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null
                ? Sort.by(sort.stream()
                        .map(sortParam -> {
                            String[] parts = sortParam.split(":");
                            String property = parts[0];
                            Sort.Direction direction =
                                    parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
                            return new Sort.Order(direction, property);
                        })
                        .toList())
                : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
