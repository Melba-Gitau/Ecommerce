package com.mpesa.mpesaservice.Repository;

import com.mpesa.mpesaservice.Model.Mpesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MpesaRepo extends JpaRepository<Mpesa, Long> {

    Optional<Mpesa> findByMerchantIdAndCheckoutId(String merchantId, String checkoutId);
}
