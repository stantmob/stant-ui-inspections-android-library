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
import br.com.stant.libraries.uilibrary.databinding.ViewingUsersDialogItemBinding;

/**
 * Created by Gabe on 25/09/2016.
 */
public class ViewingUsersDialogAdapter extends
        RecyclerView.Adapter<ViewingUsersDialogAdapter.ItemViewHolder>{

    private static final int LARGER_VALUE = 999;
    private static final int SMALLER_VALUE = 0;

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

    public ViewingUsersDialogAdapter(Context context, List<ViewingUserDto> teamMembers, ViewingUsersDialogViewContract viewingUsersDialogViewContract) {
        this.mTeamMembers = teamMembers;
        this.mContext = context;
        this.mServiceInspectionFormFilledContract = viewingUsersDialogViewContract;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewing_users_worked_days_dialog_item, parent, false);

        return new ItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        mViewHolder = holder;

        final ViewingUserDto teamMember = mTeamMembers.get(position);

        holder.bind(teamMember);

    }

    @Override
    public int getItemCount() {
        return mTeamMembers.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(ViewingUserDto teamMember) {

            TextView fieldsValue = itemView.findViewById(R.id.viewing_users_dialog_item_user_value);
            TextView fieldsName = itemView.findViewById(R.id.viewing_users_dialog_item_user_name_text_view);
            TextView fieldsFunction = itemView.findViewById(R.id.viewing_users_dialog_item_user_function_text_view);
            Button buttonLessOneDay = itemView.findViewById(R.id.viewing_users_worked_days_dialog_item_button_less);
            Button buttonMoreOneDay = itemView.findViewById(R.id.viewing_users_worked_days_dialog_item_button_More);

            showValue(teamMember, fieldsValue);
            showName(teamMember, fieldsName);
            showFunction(teamMember, fieldsFunction);
            showImage(teamMember);

            verifyApproved(buttonLessOneDay, buttonMoreOneDay);

            verifyLargerValue(teamMember.getUserWorkedDays(), buttonMoreOneDay, buttonLessOneDay);
            verifySmallerValue(teamMember.getUserWorkedDays(), buttonLessOneDay, buttonMoreOneDay);

            clickButtonLessOneDay(teamMember, fieldsValue, buttonLessOneDay, buttonMoreOneDay);
            clickButtonMoreOneDay(teamMember, fieldsValue, buttonLessOneDay, buttonMoreOneDay);

        }

        private void showImage(ViewingUserDto teamMember) {
            ImageView campoImagem = itemView.findViewById(R.id.viewing_users_dialog_item_user_photo_circular_image_view);
            if (teamMember.hasUserPhoto()) {
                Picasso.with(mContext)
                        .load(teamMember.getUserPhoto())
                        .resize(180, 180)
                        .centerCrop()
                        .placeholder(R.drawable.ic_user_holder)
                        .error(R.drawable.ic_user_holder)
                        .into(campoImagem); }
        }

        private void clickButtonMoreOneDay(ViewingUserDto teamMember, TextView fieldsValue, Button buttonLessOneDay, Button buttonMoreOneDay) {
            buttonMoreOneDay.setOnClickListener(v -> {
                teamMember.sumMoreOneDay();
                fieldsValue.setText(String.valueOf(teamMember.getUserWorkedDays()));
                verifyLargerValue(teamMember.getUserWorkedDays(), buttonMoreOneDay, buttonLessOneDay);
                verifySmallerValue(teamMember.getUserWorkedDays(), buttonLessOneDay, buttonMoreOneDay);
            });
        }

        private void clickButtonLessOneDay(ViewingUserDto teamMember, TextView fieldsValue, Button buttonLessOneDay, Button buttonMoreOneDay) {
            buttonLessOneDay.setOnClickListener(v -> {
                teamMember.removeOneDay();
                fieldsValue.setText(String.valueOf(teamMember.getUserWorkedDays()));
                verifySmallerValue(teamMember.getUserWorkedDays(), buttonLessOneDay, buttonMoreOneDay);
            });
        }

        private void verifyApproved(Button buttonLessOneDay, Button buttonMoreOneDay) {
            if (mApproved) {
                buttonMoreOneDay.setEnabled(false);
                buttonLessOneDay.setEnabled(false);
            }
        }

        private void showFunction(ViewingUserDto teamMember, TextView fieldsFunction) {
            fieldsFunction.setText(teamMember.getUserFunction());
        }

        private void showName(ViewingUserDto teamMember, TextView fieldsName) {
            fieldsName.setText(teamMember.getUserName());
        }

        private void showValue(ViewingUserDto teamMember, TextView fieldsValue) {
            fieldsValue.setText(String.valueOf(teamMember.getUserWorkedDays()));
        }

        private void verifySmallerValue(int diasTrabalhados, Button butaoDiminuiDias, Button butaoAumentaDias) {
            if (diasTrabalhados == SMALLER_VALUE) {
                butaoDiminuiDias.setAlpha(0.3f);
            } else if (diasTrabalhados < LARGER_VALUE) {
                butaoAumentaDias.setAlpha(1);
            }
        }

        private void verifyLargerValue(int diasTrabalhados, Button butaoAumentaDias, Button butaoDiminuiDias) {
            if (diasTrabalhados == LARGER_VALUE) {
                butaoAumentaDias.setAlpha(0.3f);
                ;
            } else if (diasTrabalhados > SMALLER_VALUE) {
                butaoDiminuiDias.setAlpha(1);
            }
        }

    }

}
