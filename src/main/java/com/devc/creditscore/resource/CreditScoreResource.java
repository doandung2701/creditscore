package com.devc.creditscore.resource;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devc.creditscore.dto.CreditScoreDTO;
import com.devc.creditscore.service.CreditScoreService;
import com.devc.creditscore.util.ResponseUtil;




@RestController
@RequestMapping("/api")
public class CreditScoreResource {
    private final Logger log = LoggerFactory.getLogger(CreditScoreResource.class);
    private final CreditScoreService creditScoreService;
    @Value("${config.base-value.from-value}")
    private int fromValue;
    
    @Value("${config.base-value.to-value}")
    private int toValue;
    
	public CreditScoreResource(CreditScoreService creditScoreService) {
		
		this.creditScoreService = creditScoreService;
	}
    @GetMapping("creditscore/{phoneNumber}")
    public ResponseEntity<?> getCreditScoreByPhone(@PathVariable(required = true,name = "phoneNumber")String phoneNumber){
    	log.debug("REST request to get creditscore : {}", phoneNumber);
    	return ResponseUtil.wrapOrNotFound(
    			creditScoreService.findByPhone(phoneNumber)
                    .map(c->CreditScoreDTO.build(fromValue,toValue,c)));
    }

}
