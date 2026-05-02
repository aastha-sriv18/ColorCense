package com.aastha.colorassistapp.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.aastha.colorassistapp.ColorNameFinder;
import com.aastha.colorassistapp.PixelIndicatorView;
import com.aastha.colorassistapp.R;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private View rootView;
    private ImageView imageView;
    private PixelIndicatorView pixelIndicator;
    private TextView infoText;
    private Bitmap currentBitmap;
    private String lastHexColor;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    // Zoom and Pan variables
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1.0f;
    private float lastTouchX;
    private float lastTouchY;
    private float posX = 0f;
    private float posY = 0f;
    private static final float MIN_ZOOM = 1.0f;
    private static final float MAX_ZOOM = 8.0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                loadImageFromUri(uri);
            } else {
                Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;
        imageView = view.findViewById(R.id.image_view);
        pixelIndicator = view.findViewById(R.id.pixel_indicator);
        infoText = view.findViewById(R.id.info_text);
        Button pickImageBtn = view.findViewById(R.id.btn_pick_image);
        Button colorHexaBtn = view.findViewById(R.id.btn_colorhexa);

        scaleGestureDetector = new ScaleGestureDetector(requireContext(), new ScaleListener());

        pickImageBtn.setOnClickListener(v -> launchPhotoPicker());
        colorHexaBtn.setOnClickListener(v -> openColorHexa());

        View imageContainer = view.findViewById(R.id.image_container);
        imageContainer.setOnTouchListener(this::handleTouch);
    }

    private boolean handleTouch(View v, MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);

        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!scaleGestureDetector.isInProgress() && event.getPointerCount() == 1) {
                    float x = event.getX();
                    float y = event.getY();
                    float dx = x - lastTouchX;
                    float dy = y - lastTouchY;

                    posX += dx;
                    posY += dy;

                    limitPan();
                    updateTransformation();

                    lastTouchX = x;
                    lastTouchY = y;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                // Check if it's a tap (short duration, single finger)
                if (event.getEventTime() - event.getDownTime() < 200 && event.getPointerCount() == 1) {
                    handleImageTap(event);
                }
                break;
            }
        }
        return true;
    }

    private void limitPan() {
        float width = imageView.getWidth();
        float height = imageView.getHeight();
        
        float maxTransX = (width * scaleFactor - width) / 2f;
        float maxTransY = (height * scaleFactor - height) / 2f;
        
        posX = Math.max(-maxTransX, Math.min(posX, maxTransX));
        posY = Math.max(-maxTransY, Math.min(posY, maxTransY));
    }

    private void updateTransformation() {
        imageView.setScaleX(scaleFactor);
        imageView.setScaleY(scaleFactor);
        imageView.setTranslationX(posX);
        imageView.setTranslationY(posY);

        pixelIndicator.setScaleX(scaleFactor);
        pixelIndicator.setScaleY(scaleFactor);
        pixelIndicator.setTranslationX(posX);
        pixelIndicator.setTranslationY(posY);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(MIN_ZOOM, Math.min(scaleFactor, MAX_ZOOM));
            
            limitPan();
            updateTransformation();
            return true;
        }
    }

    private void openColorHexa() {
        if (currentBitmap == null) return;
        String colorHex = lastHexColor != null ? lastHexColor.replace("#", "") : "000000";
        String url = "https://www.colorhexa.com/" + colorHex;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void launchPhotoPicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 100);
                return;
            }
        }
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            launchPhotoPicker();
        } else {
            Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadImageFromUri(Uri uri) {
        try {
            currentBitmap = android.provider.MediaStore.Images.Media.getBitmap(
                    requireContext().getContentResolver(), uri);
            imageView.setImageBitmap(currentBitmap);
            
            // Reset zoom/pan
            scaleFactor = 1.0f;
            posX = 0f;
            posY = 0f;
            updateTransformation();

            pixelIndicator.clearIndicator();
            infoText.setText("Tap on the image to pick colors");
            Button colorHexaBtn = rootView.findViewById(R.id.btn_colorhexa);
            colorHexaBtn.setVisibility(View.GONE);
        } catch (IOException e) {
            Log.e("HomeFragment", "Error loading image", e);
            Toast.makeText(getContext(), "Error loading image", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleImageTap(MotionEvent event) {
        if (currentBitmap == null) return;

        float tapX = event.getX();
        float tapY = event.getY();

        int imageViewWidth = imageView.getWidth();
        int imageViewHeight = imageView.getHeight();
        int bitmapWidth = currentBitmap.getWidth();
        int bitmapHeight = currentBitmap.getHeight();

        float scale = Math.min((float) imageViewWidth / bitmapWidth, (float) imageViewHeight / bitmapHeight);
        int displayWidth = (int) (bitmapWidth * scale);
        int displayHeight = (int) (bitmapHeight * scale);

        float paddingX = (imageViewWidth - displayWidth) / 2f;
        float paddingY = (imageViewHeight - displayHeight) / 2f;

        // Account for transformation
        float centerX = imageViewWidth / 2f;
        float centerY = imageViewHeight / 2f;
        
        float untransformedX = (tapX - centerX - posX) / scaleFactor + centerX;
        float untransformedY = (tapY - centerY - posY) / scaleFactor + centerY;

        float adjustedX = untransformedX - paddingX;
        float adjustedY = untransformedY - paddingY;

        float scaleX = (float) bitmapWidth / displayWidth;
        float scaleY = (float) bitmapHeight / displayHeight;

        int bitmapX = Math.round(adjustedX * scaleX);
        int bitmapY = Math.round(adjustedY * scaleY);

        bitmapX = Math.max(0, Math.min(bitmapX, bitmapWidth - 1));
        bitmapY = Math.max(0, Math.min(bitmapY, bitmapHeight - 1));

        if (untransformedX >= paddingX && untransformedX <= (imageViewWidth - paddingX) &&
                untransformedY >= paddingY && untransformedY <= (imageViewHeight - paddingY)) {

            int averageColor = getAverageColorInRadius(bitmapX, bitmapY, 3);
            String hexColor = colorToHex(averageColor);
            String colorDescription = ColorNameFinder.getColorName(averageColor) + " " + hexColor;
            
            lastHexColor = hexColor;
            
            pixelIndicator.updateIndicator((int) untransformedX, (int) untransformedY, averageColor, colorDescription);
            infoText.setText("Selected Color: " + colorDescription);
            
            Button colorHexaBtn = rootView.findViewById(R.id.btn_colorhexa);
            colorHexaBtn.setVisibility(View.VISIBLE);
        }
    }

    private int getAverageColorInRadius(int centerX, int centerY, int radius) {
        int totalRed = 0, totalGreen = 0, totalBlue = 0, pixelCount = 0;
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int y = centerY - radius; y <= centerY + radius; y++) {
                if (x >= 0 && x < currentBitmap.getWidth() && y >= 0 && y < currentBitmap.getHeight()) {
                    int dx = x - centerX, dy = y - centerY;
                    if (dx * dx + dy * dy <= radius * radius) {
                        int pixel = currentBitmap.getPixel(x, y);
                        totalRed += android.graphics.Color.red(pixel);
                        totalGreen += android.graphics.Color.green(pixel);
                        totalBlue += android.graphics.Color.blue(pixel);
                        pixelCount++;
                    }
                }
            }
        }
        if (pixelCount == 0) return android.graphics.Color.BLACK;
        return android.graphics.Color.rgb(totalRed / pixelCount, totalGreen / pixelCount, totalBlue / pixelCount);
    }

    private String colorToHex(int color) {
        return String.format("#%02x%02x%02x", android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color));
    }
}
