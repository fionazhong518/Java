package persistence;

import org.json.JSONObject;

public interface WriteFile {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
