package com.education.controller;

import com.education.entity.Department;
import com.education.entity.Employee;
import com.education.entity.Facsimile;
import com.education.entity.FilePool;
import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import com.education.service.facsimile.FacsimileService;
import com.education.util.Mapper.impl.DepartmentMapper;
import com.education.util.Mapper.impl.EmployeeMapper;
import com.education.util.Mapper.impl.FacsimileMapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/repository/facsimile")
@AllArgsConstructor
@Log
@ApiModel("Controller for Facsimile")
public class FacsimileController {

    @ApiModelProperty("service")
    private final FacsimileService facsimileService;

    @ApiModelProperty("mapper")
    private final FacsimileMapper mapper;

    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;

    /**
     * Method for getting facsimile
     *
     * @param id is Id facsimile in DB
     */
    @ApiOperation(value = "Получить факсимиле по id", notes = "Returns facsimile by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The Facsimile was not found")
    })
    @GetMapping("/facsimile/{id}")
    public ResponseEntity<FacsimileDTO> getFacsimile(@PathVariable("id") Long id) {
        log.info("Request to get facsimile by id = " + id);

        Facsimile facsimile = facsimileService.findById(id);
        return new ResponseEntity<>(toDTO(facsimile), HttpStatus.OK);
    }

    /**
     * Method for saving Facsimile
     *
     * @param facsimileTransefered is facsimile from client part
     */
    @ApiOperation(value = "Сохранить факсимиле")
    @PostMapping(path = "/facsimile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveFacsimile(@RequestParam("facsimile") MultipartFile facsimileTransefered,
                                                @RequestParam("employee") EmployeeDto employeeDto,
                                                @RequestParam("department") DepartmentDto departmentDto) {

        if (facsimileTransefered.isEmpty()) {
            return new ResponseEntity<>("Please select file!", HttpStatus.OK);
        }

        String facsimileFilename = facsimileTransefered.getOriginalFilename();

        String fileExtension = "";
        if (facsimileFilename != null) {
            fileExtension = facsimileFilename.substring(facsimileFilename.lastIndexOf(".") + 1);
        }
        if (!(fileExtension.equalsIgnoreCase("jpg")
                || fileExtension.equalsIgnoreCase("jpeg")
                || fileExtension.equalsIgnoreCase("png"))) {
            return new ResponseEntity<>("Wrong extension of file. Should be jpg, JPEG or PNG", HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeMapper.toEntity(employeeDto);
        Department department = departmentMapper.toEntity(departmentDto);

        Facsimile facsimile = new Facsimile(employee, department, new FilePool(/*TODO*/), true);

        try {
            facsimileService.saveFacsimile(facsimile);
            return new ResponseEntity<>("Successfully uploaded - " + facsimileFilename, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while updating", HttpStatus.BAD_REQUEST);
        }
    }

    public FacsimileDTO toDTO(Facsimile facsimile) {
        return mapper.toDto(facsimile);
    }

}
