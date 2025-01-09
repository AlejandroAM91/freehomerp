package com.github.alejandroam91.freehomerp.api.infra.data.db.accounts;

import com.github.alejandroam91.freehomerp.api.core.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountEntityMapper {
  @Mapping(target = ".", source = ".")
  Account mapEntityToModel(AccountEntity entity);
}
