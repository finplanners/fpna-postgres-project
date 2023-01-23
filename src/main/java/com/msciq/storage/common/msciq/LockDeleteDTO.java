package com.msciq.storage.common.msciq;

import lombok.Data;

import java.util.List;

@Data
public class LockDeleteDTO {
    private List<Long> ids;

    private Boolean isActive;

    private Boolean isDeleted;
}
