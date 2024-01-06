package com.example.convertlayoutaspdf;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

public class PdfConverter {
    private PdfGenerator.XmlToPDFLifecycleObserver xmlToPDFLifecycleObserver;
    public void initializePdfConverter(Activity activity){
        xmlToPDFLifecycleObserver = new PdfGenerator.XmlToPDFLifecycleObserver((ComponentActivity) activity);
       ((ComponentActivity) activity).getLifecycle().addObserver(xmlToPDFLifecycleObserver);

    }

    public void createPdf(Activity activity, int layoutID){
        PdfGenerator.getBuilder()
                .setContext((ComponentActivity) activity)
                .fromViewIDSource()
                .fromViewID(activity, layoutID)
                .setFileName("Demo-Text")
                .actionAfterPDFGeneration(PdfGenerator.ActionAfterPDFGeneration.SHARE)
                .savePDFSharedStorage(xmlToPDFLifecycleObserver)
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                        Toast.makeText(activity, "Toast Faild", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStartPDFGeneration() {

                    }

                    @Override
                    public void onFinishPDFGeneration() {

                    }

                    @Override
                    public void showLog(String log) {
                        super.showLog(log);
                        Log.d("PDF-generation",log);
                    }

                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
