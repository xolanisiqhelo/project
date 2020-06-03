package com.klweb.farmservice.mapper;

import com.klweb.farmservice.dto.UpdateFarmDto;
import com.klweb.farmservice.dto.UserDTO;
import com.klweb.farmservice.model.Farm;
import com.klweb.farmservice.model.User;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;

@Mapper(
		withIgnoreFields = "id",
		withIgnoreMissing = IgnoreMissing.ALL,
		withIgnoreNullValue = true
	)
public interface SelmaMapper {
	Farm updateFarm(UpdateFarmDto source, Farm destination);
	
	UserDTO asUserDTO(User in);
	
	User asUser(UserDTO in);
	
	// return a fresh UserDTO
	UserDTO asUserDTOfromUser(User in, UserDTO out);
}
