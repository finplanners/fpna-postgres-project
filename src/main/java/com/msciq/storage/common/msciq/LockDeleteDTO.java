package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class LockDeleteDTO {
    private List<Long> ids;

    private boolean isActive;

    private boolean isDeleted;
}
