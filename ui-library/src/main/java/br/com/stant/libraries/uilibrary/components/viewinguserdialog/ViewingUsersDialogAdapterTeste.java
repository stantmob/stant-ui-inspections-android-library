package br.com.stant.libraries.uilibrary.components.viewinguserdialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.stant.libraries.uilibrary.R;
import br.com.stant.libraries.uilibrary.databinding.ViewingUsersWorkedDaysDialogItemBinding;

/**
 * Created by Gabe on 25/09/2016.
 */
public class ViewingUsersDialogAdapterTeste extends
        RecyclerView.Adapter<ViewingUsersDialogAdapterTeste.ItemViewHolder> {

    private ItemViewHolder mViewHolder;
    private List<ViewingUserDto> mTeamMembers;
    private Context mContext;
    private ViewingUsersDialogViewContract mServiceInspectionFormFilledContract;
    private Boolean mApproved;

    public void replaceData(List<ViewingUserDto> viewingUsersDto) {
        setList(viewingUsersDto);
        notifyDataSetChanged();
    }

    public void approved(Boolean approved) {
        this.mApproved = approved;
    }

    private void setList(List<ViewingUserDto> list) {
        mTeamMembers = list;
    }

    public ViewingUsersDialogAdapterTeste(Context context, List<ViewingUserDto> teamMembers, ViewingUsersDialogViewContract viewingUsersDialogViewContract) {
        this.mTeamMembers = teamMembers;
        this.mContext = context;
        this.mServiceInspectionFormFilledContract = viewingUsersDialogViewContract;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewing_users_worked_days_dialog_item_teste, parent, false);

        return new ItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        mViewHolder = holder;

        final ViewingUserDto teamMember = mTeamMembers.get(position);

        holder.vincula(teamMember, position);

    }

    @Override
    public int getItemCount() {
        return mTeamMembers.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public void vincula(ViewingUserDto teamMember, int posicao) {

            TextView campoValor = itemView.findViewById(R.id.viewing_users_dialog_item_user_value);
            TextView campoNome = itemView.findViewById(R.id.viewing_users_dialog_item_user_name_text_view);
            campoNome.setText(teamMember.getUserName());
            TextView campoFuncao = itemView.findViewById(R.id.viewing_users_dialog_item_user_function_text_view);
            campoFuncao.setText(teamMember.getUserFunction());

            Button butaoDiminuiDias = itemView.findViewById(R.id.viewing_users_worked_days_dialog_item_button_less);
            butaoDiminuiDias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    teamMember.removeOneDay();
                    campoValor.setText(String.valueOf(teamMember.getUserWorkedDays()));

                }
            });

            Button butaoAumentaDias = itemView.findViewById(R.id.viewing_users_worked_days_dialog_item_button_most);
            butaoAumentaDias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    teamMember.sumMoreOneDay();
                    campoValor.setText(String.valueOf(teamMember.getUserWorkedDays()));
                }
            });

            ImageView campoImagem = itemView.findViewById(R.id.viewing_users_dialog_item_user_photo_circular_image_view);
            if (teamMember.hasUserPhoto()) {
                Picasso.with(mContext)
                        .load(teamMember.getUserPhoto())
                        .resize(180, 180)
                        .centerCrop()
                        .placeholder(R.drawable.ic_user_holder)
                        .error(R.drawable.ic_user_holder)
                        .into(campoImagem);

            }


        }

    }
}
