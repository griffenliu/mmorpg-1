package fr.jamailun.Mmorpg.Classes;

import java.util.ArrayList;
import java.util.List;

public class JobExtension {

	private MmoPlayer player;
	private Job slot1;
	private Job slot2;
	private Job slot3;
	
	//constru
	public JobExtension(MmoPlayer player, boolean exist) {
		this.player = player;
		
		if(exist) {
			this.slot1 = player.getJob(1);
			this.slot2 = player.getJob(2);
			this.slot3 = player.getJob(3);
			
		} else {
			this.slot1 = new Job(player, 1);
			this.slot2 = new Job(player, 2);
			this.slot3 = new Job(player, 3);
			this.save();
		}
		/*
		player.sendMessage("slot 1 = "+ this.slot1.getName());
		player.sendMessage("---lvl = "+ this.slot1.getLevel());
		player.sendMessage("--- xp = "+ this.slot1.getXp());
		*/
	}
	
	//getters
	public Job getJob1() {
		return this.slot1;
	}
	
	public Job getJob2() {
		return this.slot2;
	}
	
	public Job getJob3() {
		return this.slot3;
	}
	
	public List<Job> getJobs() {
		List<Job> list = new ArrayList<Job>();
		list.add(slot1);
		list.add(slot2);
		list.add(slot3);
		return list;
	}
	
	public MmoPlayer getOwner() {
		return this.player;
	}
	
	//setters
	public void setJob(int slot, Job job) {
		if(slot == 1) this.slot1 = job;
		if(slot == 2) this.slot2 = job;
		if(slot == 3) this.slot3 = job;
	}
	
	public void save() {
		slot1.setSlot(1);slot1.save();
		slot1.setSlot(2);slot2.save();
		slot1.setSlot(3);slot3.save();
	}
}
