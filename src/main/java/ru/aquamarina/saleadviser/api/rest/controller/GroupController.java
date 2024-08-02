package ru.aquamarina.saleadviser.api.rest.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.aquamarina.saleadviser.api.rest.dto.GroupAndProductDTO;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.api.rest.dto.GroupRowDTO;

import java.io.File;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping("/dictionary/group")
public interface GroupController {

    @GetMapping
    ResponseEntity<List<GroupDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<GroupDTO> getById(@NonNull @PathVariable("id") UUID id);

    @PostMapping
    ResponseEntity<GroupDTO> save(@NonNull @RequestBody GroupDTO groupDTO);

    @PutMapping("/{id}")
    ResponseEntity<GroupDTO> update(@NonNull @PathVariable("id") UUID id,
                                    @NonNull @RequestBody GroupDTO groupDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@NonNull @PathVariable("id") UUID id);

    @GetMapping("/{id}/product")
    ResponseEntity<List<GroupAndProductDTO>> getAllGroupAndProduct(@NonNull @PathVariable("id") UUID id);

    @PostMapping("/{id}/product")
    ResponseEntity<GroupAndProductDTO> addGroupAndProduct(@NonNull @PathVariable("id") UUID id,
                                                          @NonNull @RequestBody GroupAndProductDTO groupRowDTO);

    @GetMapping("/{id}/product/{productId}")
    ResponseEntity<GroupAndProductDTO> getGroupAndProduct(@NonNull @PathVariable("id") UUID id,
                                                          @NonNull @PathVariable("productId") UUID productId);

    @PutMapping("/{id}/product/{productId}")
    ResponseEntity<GroupAndProductDTO> updateGroupAndProduct(@NonNull @PathVariable("id") UUID id,
                                                             @NonNull @PathVariable("productId") UUID productId,
                                                             @NonNull @RequestBody GroupAndProductDTO groupRowDTO);

    @DeleteMapping("/{id}/product/{productId}")
    ResponseEntity<?> deleteGroupAndProduct(@NonNull @PathVariable("id") UUID id,
                                            @NonNull @PathVariable("productId") UUID productId);

    @GetMapping("/{id}/product/{productId}/row")
    ResponseEntity<GroupRowDTO> getGroupRow(@NonNull @PathVariable("id") UUID id,
                                            @NonNull @PathVariable("productId") UUID productId);

    @GetMapping("/{id}/rows")
    ResponseEntity<List<GroupRowDTO>> getGroupRows(@NonNull @PathVariable("id") UUID id);

    // maybe return InputStreamResource
    @GetMapping(value = "/{id}/prediction/file/{date}")
    ResponseEntity<InputStreamResource> getPredictionFile(@NonNull @PathVariable("id") UUID uuid,
                                                          @NonNull @PathVariable("date") ZonedDateTime dateTime);
}
