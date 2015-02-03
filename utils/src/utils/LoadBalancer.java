package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class LoadBalancer {
	private HashMap<String, Integer> server_pool; // server id - index
	private ArrayList<String> slots;

	LoadBalancer() {
		server_pool = new HashMap<>();
		slots = new ArrayList<>();
	}

	/*
	 * Operation should run in O(1) time
	 */
	boolean addServer(String hostId) {
		if (server_pool.containsKey(hostId)) {
			return false;
		}
		Integer slot_id = -1;
		if (slots.size() > server_pool.size()) {
			slot_id = server_pool.size();
			slots.set(slot_id, hostId);
		} else if (slots.add(hostId)) {
			slot_id = slots.size() - 1;
		}

		if (slot_id >= 0) {
			server_pool.put(hostId, slot_id);
		}
		return slot_id >= 0;
	}

	/*
	 * Operation should run in O(1) time
	 */
	boolean deleteServer(String hostId) {
		if (!server_pool.containsKey(hostId)) {
			return false;
		}

		Integer slot_id = server_pool.get(hostId);
		if (slot_id == server_pool.size() - 1) { // the last valid hostid in
													// 'slots'
			slots.set(slot_id, "");
		} else { // move the last valid hostid here
			slots.set(slot_id, slots.get(server_pool.size() - 1));
			// update the moved hostid's slot id
			server_pool.put(slots.get(slot_id), slot_id);
		}
		server_pool.remove(hostId);
		return true;
	}

	/*
	 * Operation should run in O(1) time
	 */
	String getRandomServer() {
		int slot_id = new Random().nextInt(server_pool.size());
		return slots.get(slot_id);
	}
}

