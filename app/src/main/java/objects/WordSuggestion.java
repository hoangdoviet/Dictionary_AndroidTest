package objects;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

@SuppressLint("ParcelCreator")
public class WordSuggestion implements SearchSuggestion {

    Word word;

    public WordSuggestion(Word word) {
        this.word = word;
    }

    @Override
    public String getBody() {
        return word.getKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
