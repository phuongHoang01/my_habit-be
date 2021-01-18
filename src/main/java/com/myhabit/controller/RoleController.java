package com.myhabit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhabit.dto.role.RoleAddDTO;
import com.myhabit.entities.Role;
import com.myhabit.service.RoleService;


@RestController
@RequestMapping("/api/admin/role")
public class RoleController {

	final String EMPTY_LIST = "Danh sách rỗng";
	final String ROLE_EXIST = "Quyền này đã tồn tại";
	final String ADD_SUCCESSFUL = "Thêm quyền thành công";
	
	RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("/get-all-role")
	public ResponseEntity<List<Role>> getAllRoleIsActive() {
		try {
			List<Role> roles = roleService.findAllByActive(Boolean.TRUE);
			
			if(roles.size() == 0) {
				return new ResponseEntity(EMPTY_LIST,HttpStatus.OK);
			}
			
			return new ResponseEntity<List<Role>>(roles,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/add-role")
	public ResponseEntity addRole(@RequestBody RoleAddDTO roleAddDTO) {
		try {
			int record = roleService.countByName(roleAddDTO.getName());
			
			if(record > 0) {
				return new ResponseEntity(ROLE_EXIST, HttpStatus.BAD_REQUEST);
			}
			
			roleService.addRole(roleAddDTO);
			return new ResponseEntity(ADD_SUCCESSFUL,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
