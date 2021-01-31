package com.example.picapp.dummy;
import com.example.picapp.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */

public class DummyContent {

//    public static String[] nameArray = {"Cappuccino Oriental", "Flat white", "Soft ginger latte",
//            "Hazelnut affogato", "Latte hazelnut", "Long black ice", "Esspresso macchiato",
//             "Mocca latte", "Reverso intenso", "Tiramisu cappuccino"};

//    public static Integer[] imageId = {R.drawable.cappuccino, R.drawable.flatwhite, R.drawable.gingerlatte,
//                          R.drawable.hazelapo, R.drawable.lattehazel, R.drawable.longblackice, R.drawable.macchiato,
//                          R.drawable.mochalatte, R.drawable.reversointenso, R.drawable.tiracapu};


    /**
     * An array of sample (dummy) items.
     */
    public static final ArrayList<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();


//    private static final int COUNT = nameArray.length;
//
//    static {
//        for (int i = 1; i <= COUNT; i++) {
////            addItem(createDummyItem(i));
//            String temp = Integer.toString(i);
//            addItem(new DummyItem(temp, nameArray[i-1], imageId[i-1]));
//        }
//    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String pic_name;
        public final int pic_image;

        public DummyItem(String id, String pic_name, int pic_image) {
            this.id = id;
            this.pic_name = pic_name;
            this.pic_image = pic_image;
        }

        @Override
        public String toString() {
            return pic_name;
        }
    }
}
