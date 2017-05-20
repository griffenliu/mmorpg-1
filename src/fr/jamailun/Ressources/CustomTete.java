package fr.jamailun.Ressources;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

public enum CustomTete {

	/*man:
	 *  ff37a86d-01b6-48d7-ba86-29827461c812
	 *  eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzNlNWZiZmU4ZjZhNTI5MjQ5ZDNjOWI4ODgxNmM5Njk1OTA2ZWU5MTc1MjI5OTllNGQwZWVlYzUxNTRhOTJlIn19fQ==
	 * zombie 1:
	 *  bff1b4bc-6601-4728-906a-8484560f5c9b
	 *  eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRhMTRiOTdhMjBjNWE0NGZkZDllMWQ5ZWMzNzlkZDNhYWEyNWViYmVjOTdmNzlkYjI3ZDE4YzJiNTNlZDEifX19
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	//NOM, UUID, TEXTURE
	
	ORC("orc", UUID.fromString("08824ca5-4b2f-44a9-8952-0c1fcfeb560b"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmUxODNhMjYyYjI2NjNmMTlmMGYyYTA4NjQ0NGRhZWQ0OWI5NzczZmZiNTUyMTE1ZTNlZDlmZmZlMWJkYTEzIn19fQ=="),
	VAGABOND("vagabond", UUID.fromString("ff37a86d-01b6-48d7-ba86-29827461c812"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzNlNWZiZmU4ZjZhNTI5MjQ5ZDNjOWI4ODgxNmM5Njk1OTA2ZWU5MTc1MjI5OTllNGQwZWVlYzUxNTRhOTJlIn19fQ=="),
	ZOMBIE1("zombie_1", UUID.fromString("bff1b4bc-6601-4728-906a-8484560f5c9b"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRhMTRiOTdhMjBjNWE0NGZkZDllMWQ5ZWMzNzlkZDNhYWEyNWViYmVjOTdmNzlkYjI3ZDE4YzJiNTNlZDEifX19"),
	
	
	;
	
	public String name;
	public UUID id;
	public String texture;
	
	private CustomTete(String name, UUID id, String texture) {
		this.name= name;
		this.id = id;
		this.texture = texture;
	}
	
	public static ItemStack createHead(CustomTete tete){
		GameProfile profile = createGameProfile(tete.texture, tete.id);
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		ItemMeta headMeta = head.getItemMeta();
		Class<?> headMetaClass = headMeta.getClass();
		if(!set(headMetaClass, headMeta, "profile", profile, "Unable to inject profile")) {
			return null;
		}
		head.setItemMeta(headMeta);
		SkullMeta skullMeta = (SkullMeta)head.getItemMeta();
		head.setItemMeta(skullMeta);
		return head;
	}

	private static GameProfile createGameProfile(String texture, UUID id){
		GameProfile profile = new GameProfile(id, null);
		PropertyMap propertyMap = profile.getProperties();
		if(propertyMap == null){
			Bukkit.getLogger().log(Level.WARNING, "No property map found in GameProfile, can't continue.");
			return null;
		}
		propertyMap.put("textures", new Property("textures", texture));
		propertyMap.put("Signature", new Property("Signature", "1234"));
		return profile;
	}

	private static boolean set(Class<?> sourceClass, Object instance, String fieldName, Object value, String message){
		try{
			Field field = sourceClass.getDeclaredField(fieldName);
			boolean accessible = field.isAccessible();
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			int modifiers = modifiersField.getModifiers();
			boolean isFinal = (modifiers & 0x10) == 16;
			if (!accessible) {
				field.setAccessible(true);
			}
			if (isFinal){
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, modifiers & 0xFFFFFFEF);
			}
			try{
				field.set(instance, value);
			}
			finally
			{
				if (isFinal) {
					modifiersField.setInt(field, modifiers | 0x10);
				}
				if (!accessible) {
					field.setAccessible(false);
				}
			}
			return true;
		}
		catch (IllegalArgumentException ex){
			Bukkit.getLogger().log(Level.SEVERE, message + ": unsupported version.");
		}
		catch (IllegalAccessException ex){
			Bukkit.getLogger().log(Level.SEVERE, message + ": security exception.");
		}
		catch (NoSuchFieldException ex){
			Bukkit.getLogger().log(Level.SEVERE, message + ": unsupported version, field " + fieldName + " not found.");
		}
		catch (SecurityException ex){
			Bukkit.getLogger().log(Level.SEVERE, message + ": security exception.");
		}
	return false;
	}
}