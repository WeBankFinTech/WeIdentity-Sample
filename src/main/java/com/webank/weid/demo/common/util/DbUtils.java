package com.webank.weid.demo.common.util;

import java.util.HashMap;
import java.util.Map;

import com.webank.weid.demo.command.BaseBean;
import com.webank.weid.demo.command.DemoBase;
import com.webank.weid.protocol.base.Challenge;
import com.webank.weid.protocol.base.ClaimPolicy;
import com.webank.weid.protocol.base.PresentationPolicyE;

public class DbUtils extends DemoBase {

    // verifier WeId
    private static String VERIFIER_WEID = "did:weid:0x0231765e19955fc65133ec8591d73e9136306cd0";

    /**
     * mock query the challenge form DB.
     * @param nonce the nonce
     * @return the challenge
     */
    public static Challenge queryChallenge(String nonce) {
        Challenge challenge = new Challenge();
        // 随机字符串
        challenge.setNonce(nonce);
        challenge.setVersion(1);
        // 用户的WeId
        // challenge.setWeId(FileUtils.getDataByPath("userWeId.json"));
        BaseBean.print("queryChallenge result:");
        BaseBean.print(challenge);
        return challenge;
    }

    /**
     * mock query the policy from DB.
     * @param policyId the policyId
     * @return the PresentationPolicyE
     */
    public static PresentationPolicyE queryPresentationPolicyE(Integer policyId) {
        PresentationPolicyE presentationPolicyE = new PresentationPolicyE();
        presentationPolicyE.setId(policyId);
        presentationPolicyE.setVersion(1);
        // verify机构的weid
        presentationPolicyE.setWeId(VERIFIER_WEID);
        presentationPolicyE.setOrgId("");
        Map<Integer, ClaimPolicy> claimPolicyMap = queryPolicy();
        presentationPolicyE.setPolicy(claimPolicyMap);
        BaseBean.print("queryPresentationPolicyE result:");
        BaseBean.print(presentationPolicyE);
        return presentationPolicyE;
    }

    private static Map<Integer, ClaimPolicy> queryPolicy() {
        
        ClaimPolicy claim2000000 = new ClaimPolicy();
        claim2000000.setCptId(2000000);
        String disclosed = DemoBase.DISCLOSURE1;
        claim2000000.setFieldsToBeDisclosed(disclosed);
        claim2000000.setVersion(1);
        
        Map<Integer, ClaimPolicy> policy = new HashMap<>();
        policy.put(2000000, claim2000000);

        ClaimPolicy claim2000001 = new ClaimPolicy();
        claim2000001.setCptId(2000001);
        String disclosed2 = DemoBase.DISCLOSURE2;
        claim2000001.setFieldsToBeDisclosed(disclosed2);
        claim2000001.setVersion(1);
        policy.put(2000001, claim2000001);
        return policy;
    }
}
