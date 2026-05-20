package br.edu.ifsul.pdm;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsul.pdm.R;

public class ModeloDeLinha extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView deleteButton;

    public ModeloDeLinha(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.txtConteudo);
        deleteButton = itemView.findViewById(R.id.btnExcluir);
    }
}