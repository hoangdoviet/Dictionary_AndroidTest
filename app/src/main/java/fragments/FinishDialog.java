package fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.mydictionary.R;

public class FinishDialog extends DialogFragment {

    TextView tvText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.dialog_finish_quest, null);
        builder.setView(customLayout);
        builder.setTitle("Finished!");
        builder.setCancelable(true);

        tvText = customLayout.findViewById(R.id.dialog_finish_quest_text);
        return builder.create();
    }

    public void setText(String str){
        tvText.setText(str);
    }
}
