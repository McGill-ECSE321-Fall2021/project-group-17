package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ItemInstanceDTO;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

    @Autowired
    private ItemService service;

    @GetMapping(value = {"/items","/items/"})
    public List<ItemInstanceDTO> viewInventory(){
        return service.viewInventory().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    //TODO change to item
    private ItemInstanceDTO convertToDto(ItemInstance item) {
        if (item == null) {
            throw new IllegalArgumentException("There is no such item");
        }
        ItemInstanceDTO itemInstanceDTO = new ItemInstanceDTO();
        itemInstanceDTO.setSystem(item.getSystem());
        itemInstanceDTO.setCheckableItem(item.getCheckableItem());
        itemInstanceDTO.setSerialNum(item.getSerialNum());
        return itemInstanceDTO;
    }
}
