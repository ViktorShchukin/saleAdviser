package ru.aquamarina.saleadviser.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.aquamarina.saleadviser.api.rest.dto.GroupDTO;
import ru.aquamarina.saleadviser.api.rest.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

@RestController
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
    ResponseEntity<List<ProductDTO>> getAllProduct(@NonNull @PathVariable("id") UUID id);

    @PostMapping("/{id}/product/{productId}")
    ResponseEntity<ProductDTO> saveToGroup(@NonNull @PathVariable("id") UUID id,
                                           @NonNull @PathVariable("productId") UUID productId,
                                           @NonNull @RequestBody ProductDTO productDTO);

    @DeleteMapping("/{id}/product/{productId}")
    ResponseEntity<?> deleteFromGroup(@NonNull @PathVariable("id") UUID id,
                                      @NonNull @PathVariable("productId") UUID productId);
}
