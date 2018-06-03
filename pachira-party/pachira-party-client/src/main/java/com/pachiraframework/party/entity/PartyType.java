package com.pachiraframework.party.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团体类型
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper=true)
public class PartyType extends BaseEntity<String> {
	private static final long serialVersionUID = 1045065374462732076L;
	private String parentId;
	private String description;
	
	@Getter
	@AllArgsConstructor
	public static enum PartyTypeEnum {
		PERSON, AUTOMATED_AGENT, CORPORATION, FAMILY, GOVERNMENT_AGENCY, INFORMAL_GROUP, LEGAL_ORGANIZATION, PARTY_GROUP, TEAM;
	}
}
