package cn.edu.nuc.editordemo_084131;

import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

public class SizeListener implements View.OnClickListener {

    private EditText editText = null;

    public SizeListener(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void onClick(View v) {

        float size = editText.getTextSize();
        switch (v.getId()){
            case R.id.bigger:
                size = size + 2.0f;
                break;
            case R.id.smaller:
                size = size - 2.0f;
                default:break;
        }
        if(size>=72)
            size = 72;
        if(size<=6)
            size = 6;
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }
}
