package com.karthee.ecommerceapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.karthee.ecommerceapp.ui.main.MainFragment
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity
import com.paytabs.paytabs_sdk.utils.PaymentParams


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {
            Log.e("Tag", data!!.getStringExtra(PaymentParams.RESPONSE_CODE))
            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID))
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN)!!.isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN))
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL))
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD))
            }
        }
    }

    fun checkout(view: View) {        val intent = Intent(applicationContext, PayTabActivity::class.java)
        intent.putExtra(
            PaymentParams.MERCHANT_EMAIL,
            "karthegeyan.k@gmail.com"
        ) //this a demo account for testing the sdk

        intent.putExtra(PaymentParams.SECRET_KEY, "f0bfMtUbBFP16R5r8NkDoB6ffb38ORZ4RajEDdPfTEh0wnrFbMizooOEASMpJLtBWNfA05PUJ7UfumUF8dewB3acVXBFJ5hFSxfT") //Add your Secret Key Here

        intent.putExtra(PaymentParams.LANGUAGE, PaymentParams.ENGLISH)
        intent.putExtra(PaymentParams.TRANSACTION_TITLE, "Test Paytabs android library")
        intent.putExtra(PaymentParams.AMOUNT, 5.0)

        intent.putExtra(PaymentParams.CURRENCY_CODE, "BHD")
        intent.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "009733")
        intent.putExtra(PaymentParams.CUSTOMER_EMAIL, "customer-email@example.com")
        intent.putExtra(PaymentParams.ORDER_ID, "123456")
        intent.putExtra(PaymentParams.PRODUCT_NAME, "Product 1, Product 2")

//Billing Address

//Billing Address
        intent.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 1,Building 123, Road 2345")
        intent.putExtra(PaymentParams.CITY_BILLING, "Manama")
        intent.putExtra(PaymentParams.STATE_BILLING, "Manama")
        intent.putExtra(PaymentParams.COUNTRY_BILLING, "BHR")
        intent.putExtra(
            PaymentParams.POSTAL_CODE_BILLING,
            "00973"
        ) //Put Country Phone code if Postal code not available '00973'


//Shipping Address

//Shipping Address
        intent.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345")
        intent.putExtra(PaymentParams.CITY_SHIPPING, "Manama")
        intent.putExtra(PaymentParams.STATE_SHIPPING, "Manama")
        intent.putExtra(PaymentParams.COUNTRY_SHIPPING, "BHR")
        intent.putExtra(
            PaymentParams.POSTAL_CODE_SHIPPING,
            "00973"
        ) //Put Country Phone code if Postal code not available '00973'


//Payment Page Style

//Payment Page Style
        intent.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#2474bc")

//Tokenization

//Tokenization
        intent.putExtra(PaymentParams.IS_TOKENIZATION, true)
        startActivityForResult(intent, PaymentParams.PAYMENT_REQUEST_CODE)
    }

}