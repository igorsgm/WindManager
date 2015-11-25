package model;

import java.io.IOException;

import helper.WebsiteReader;

public class Character {

	//Attributes
	private Account charAcc;	// account id the character belongs - ex: acc 1, 2, 3
	private String name;
	private int level;			// current level
	private String world;
	private int weaponSkill;
	private int shieldSkill;
	private String vocation;
	private String status;		//online or offline
	private int stamina;		// current stamina
	private int bankBalance;	// current bank balance

	//Constructor method
	public Character(Account charAcc, String name, String vocation, int currentStamina, int bankBalance){
		this.setCharAcc(charAcc);
		this.setName(name);
		this.setVocation(vocation);
		this.setStamina(currentStamina);
		this.setBankBalance(bankBalance);
	}

	//Getters and setters
	public Account getCharAcc() {
		return charAcc;
	}

	public void setCharAcc(Account charAcc) {
		this.charAcc = charAcc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		WebsiteReader websiteReader = new WebsiteReader(this.getName(), "Level");
		try {
			this.setLevel(Integer.parseInt(websiteReader.characterInfoReader()));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getWorld() {
		WebsiteReader websiteReader = new WebsiteReader(this.getName(), "World");
		try {
			this.setWorld(websiteReader.characterInfoReader());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public int getWeaponSkill() {
		return weaponSkill;
	}

	public void setWeaponSkill(int weaponSkill) {
		this.weaponSkill = weaponSkill;
	}

	public int getShieldSkill() {
		return shieldSkill;
	}

	public void setShieldSkill(int shieldSkill) {
		this.shieldSkill = shieldSkill;
	}

	public String getVocation() throws IOException {
		WebsiteReader websiteReader = new WebsiteReader(this.getName(), "Vocation");
		try {
			this.setVocation(websiteReader.characterInfoReader());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public String getStatus() throws IOException {
		WebsiteReader websiteReader = new WebsiteReader(this.getName());
		try {
			this.setStatus(websiteReader.characterStatusReader());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(int bankBalance) {
		this.bankBalance = bankBalance;
	}
	
	public String toString(){
		return this.getName();
	}

}
