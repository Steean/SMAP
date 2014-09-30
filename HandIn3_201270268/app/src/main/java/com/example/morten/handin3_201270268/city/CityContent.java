package com.example.morten.handin3_201270268.city;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CityContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<CityItem> ITEMS = new ArrayList<CityItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, CityItem> ITEM_MAP = new HashMap<String, CityItem>();

    static {
        // Add 3 sample items.
        addItem(new CityItem("1", "Harboore",  "The City Morten comes from"));
        addItem(new CityItem("2", "Grauballe", "The City Stian comes from"));
        addItem(new CityItem("3", "Aarhus", "The city we study in"));
    }

    private static void addItem(CityItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class CityItem {
        public String id;
        public String content;
        public String description;

        public CityItem(String id, String content, String description) {
            this.id = id;
            this.content = content;
            this.description = description;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
