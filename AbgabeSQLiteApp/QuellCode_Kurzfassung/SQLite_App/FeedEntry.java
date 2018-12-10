package davidderichs.SQLite_App;
import android.provider.BaseColumns;

public abstract class FeedEntry  implements BaseColumns {
    public static final String TABLE_NAME = "entry";
    public static final String COLUMN_NAME_ENTRY_ID = "Id";
    public static final String COLUMN_NAME_NAME = "Name";
    public static final String COLUMN_NAME_LASTNAME = "Nachname";
    public static final String COLUMN_NAME_NICKNAME = "Spitzname";
    public static final String COLUMN_NAME_PHONENUMBER = "Telefonnummber";
}
