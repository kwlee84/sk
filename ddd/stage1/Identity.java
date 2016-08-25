package javastory.club.stage1.step42.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Identity {
	//
    private final String id;

    public Identity() {
        this.id = UUID.randomUUID().toString();
    }

    public Identity(String id) {
    	this.id = id; 
    }
    
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object target) {
    	//
        if (this == target) {
        	return true;
        }
        
        if (target == null || getClass() != target.getClass()) {
        	return false;
        }
        
        Identity identity = (Identity) target;
        return Objects.equals(id, identity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + id.substring(0,8) + "...]";
    }
}