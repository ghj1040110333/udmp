/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2015 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package cn.com.git.udmp.test.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author liang
 *
 */
@Service
public class CacheService {
    @Cacheable(value="sysCache",key="1")
    public String cache(){
        return "this is mine cache";
        
    }

    /**
     * @return
     */
    @Cacheable(value="sysCache",key="1")
    public String cache1() {
        return "this is cache1";
    }
}
