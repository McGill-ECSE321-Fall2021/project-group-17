package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ItemInstanceDTO;
import ca.mcgill.ecse321.library.model.ItemInstance;
import ca.mcgill.ecse321.library.service.ItemInstanceService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ItemInstanceRestController {
    @Autowired
    private ItemInstanceService service;

    @GetMapping(value = {"/iteminstance/{serialNum}", "/iteminstance/{serialNum}/"})
    public ItemInstanceDTO getItemInstance(@PathVariable("serialNum") int serialNum) throws IllegalArgumentException {
        ItemInstance itemInstance = service.getItemInstance(serialNum);
        return convertToDTO(itemInstance);
    }

    @GetMapping(value = {"/iteminstances/", "/iteminstances"})
    public List<ItemInstanceDTO> getItemInstances() throws IllegalArgumentException {
        return service.viewInventory().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping(value= {"/iteminstance/", "/iteminstance"})
    @ResponseBody
    public ItemInstanceDTO createItemInstance(@RequestBody JsonBody body) throws IllegalArgumentException{
        ItemInstance itemInstance = service.createItemInstance(body.getCheckableItemId());
        return convertToDTO(itemInstance);
    }

    private ItemInstanceDTO convertToDTO(ItemInstance itemInstance) {
        if (itemInstance == null) {
            throw new IllegalArgumentException("There is no such ItemInstance");
        }

        ItemInstanceDTO itemInstanceDTO = new ItemInstanceDTO();
        itemInstanceDTO.setSerialNum(itemInstance.getSerialNum());
        itemInstanceDTO.setCheckableItem(itemInstance.getCheckableItem());
        return itemInstanceDTO;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static class JsonBody{
        Integer checkableItemId;

        public Integer getCheckableItemId() {
            return checkableItemId;
        }

        public void setCheckableItemId(Integer checkableItemId) {
            this.checkableItemId = checkableItemId;
        }


        public JsonBody(){}
    }
}
