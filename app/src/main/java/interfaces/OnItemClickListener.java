package interfaces;

import android.view.View;

public interface OnItemClickListener {
    public void onClick(View view, int position);
    void onSubmit(View view, int position, boolean isCorrect);
}
