package com.creatubbles.api.response.amazon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;

/**
 * Created by Jevgeni on 28.10.2015.
 */
@APIVersion(2)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadS3FileResponse extends CreatubblesResponse {

    private boolean success;
}
