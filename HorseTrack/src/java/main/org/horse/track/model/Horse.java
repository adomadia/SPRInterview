package org.horse.track.model;

public class Horse{

	private Long id;
	private String name;
	private Integer odds;

	public Horse(String name, Integer odds) {
		this.name = name;
		this.odds = odds;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setOdds(Integer odds) {
		this.odds = odds;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOdds() {
		return odds;
	}
}
