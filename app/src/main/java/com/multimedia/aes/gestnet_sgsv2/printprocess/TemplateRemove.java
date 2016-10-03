package com.multimedia.aes.gestnet_sgsv2.printprocess;

import android.content.Context;
import android.os.Message;

import com.brother.ptouch.sdk.PrinterInfo.ErrorCode;
import com.brother.ptouch.sdk.PrinterStatus;
import com.brother.ptouch.sdk.TemplateInfo;
import com.multimedia.aes.gestnet_sgsv2.common.Common;
import com.multimedia.aes.gestnet_sgsv2.common.MsgDialog;
import com.multimedia.aes.gestnet_sgsv2.common.MsgHandle;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class TemplateRemove extends BasePrint {
    public final static int COMMAND_REMOVE = 0;
    public final static int COMMAND_GET_LIST = 1;

    private TemplateRemoveListener mListener = null;

    public TemplateRemove(Context context, MsgHandle mHandle, MsgDialog mDialog) {
        super(context, mHandle, mDialog);
    }

    /**
     * Template list are acquired by the printer
     *
     * @param listener The results are reported in listener
     */
    public void getTemplateList(TemplateRemoveListener listener) {

        this.mListener = listener;
        mCancel = false;
        RemoveThred pref = new RemoveThred();
        pref.getTemplateList();
    }

    /**
     * Template list are removed by the printer
     *
     * @param key      key of removed tempaltes
     * @param listener The results are reported in listener
     */
    public void removeTemplate(List<Integer> key,
                               TemplateRemoveListener listener) {
        this.mListener = listener;
        mCancel = false;
        RemoveThred pref = new RemoveThred();
        pref.removeTemaplate(key);
    }

    @Override
    protected void doPrint() {
    }

    public interface TemplateRemoveListener extends EventListener {
        public void finish(PrinterStatus status, List<TemplateInfo> tempList);
    }

    private class RemoveThred extends Thread {
        private List<TemplateInfo> mTempList;
        private int mCommandType = -1;
        private List<Integer> mKey;

        public void getTemplateList() {
            this.mCommandType = COMMAND_GET_LIST;
            mTempList = new ArrayList<TemplateInfo>();
            start();
        }

        public void removeTemaplate(List<Integer> keys) {
            this.mCommandType = COMMAND_REMOVE;
            mTempList = new ArrayList<TemplateInfo>();
            this.mKey = keys;
            start();
        }

        @Override
        public void run() {

            setPrinterInfo();

            Message msg = mHandle.obtainMessage(Common.MSG_TRANSFER_START);
            mHandle.sendMessage(msg);
            mHandle.setFunction(MsgHandle.FUNC_TRANSFER);
            mPrintResult = new PrinterStatus();
            if (!mCancel) {
                if (mCommandType == COMMAND_GET_LIST) {
                    mPrintResult = mPrinter.getTemplateList(mTempList);

                } else if (mCommandType == COMMAND_REMOVE) {
                    mPrinter.startCommunication();
                    if (!mCancel) {
                        mPrintResult = mPrinter.removeTemplate(mKey);
                    } else {
                        mPrintResult.errorCode = ErrorCode.ERROR_CANCEL;
                    }
                    if (!mCancel && mPrintResult.errorCode == ErrorCode.ERROR_NONE) {
                        mPrintResult = mPrinter.getTemplateList(mTempList);
                    } else if (mCancel && mPrintResult.errorCode == ErrorCode.ERROR_NONE) {
                        mPrintResult.errorCode = ErrorCode.ERROR_CANCEL;
                    }
                    mPrinter.endCommunication();
                }
            } else {
                mPrintResult.errorCode = ErrorCode.ERROR_CANCEL;
            }

            if (mListener != null) {
                mListener.finish(mPrintResult, mTempList);
            }

            // end message
            mHandle.setResult(showResult());
            mHandle.setBattery(getBattery());

            msg = mHandle.obtainMessage(Common.MSG_DATA_SEND_END);
            mHandle.sendMessage(msg);
        }
    }

}