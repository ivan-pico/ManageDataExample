package util;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.ivanpico.managedata.managedataexample.BuildConfig;

import java.util.Calendar;

public class GlobalConstant {

    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final int ADMIN_ROLE = 1;
    public static TextView textView = null;

    // URL SERVICE RUFOLA "
    //https://vps424302.ovh.net/api/v2/db/_table/CurrentMeteoProvider

    public final static String HOST = "https://openexchangerates.org";

    public final static String SERVICE_TYPE = "/api/";
    public final static String SERVICE_URL = HOST + SERVICE_TYPE;

      // global shared preferences
    public static final String DATAUSER_SHARE_PREFERENCE = "DATA_USER_SHARED_PREFERENCES";
    public static final String DATAUSER_PASS_SHARE_PREFERENCE = "DATA_USER_PASS_SHARED_PREFERENCES";


    public static void glLog(String message) {

        if (DEBUG) {
            Log.i("RUFOLA LOG --> ", message);
            if (textView != null)
                textView.setText(message);
        }
    }


    public static void glLog(TextView textView, String message) {

        if (DEBUG) {
            Log.i("APP EXAMPLE LOG --> ", message);
            if (textView != null)
                textView.setText(message);
        }

    }

    public static void toastIt(Context context, String message) {

        if (DEBUG) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            glLog(message);
        }
    }

    public final static String TEXT_PRIVACY_POLICY = " Example of 'Terms And Conditions Of Website Use'\n" +
            "\n" +
            "Please read these terms and conditions carefully before using this site\n" +
            "\n" +
            "These terms and conditions (together with the documents referred to in it) tells you the terms and conditions on which you may make use of our website https://openexchangerates.org and its associated domains, subdomains and application programming interface (\"API\") (collectively, our site), whether as a guest or a registered user. Use of our site includes accessing, browsing, or registering to use our site.\n" +
            "\n" +
            "Please read these terms and conditions carefully before you start to use our site, as these will apply to your use of our site.\n" +
            "\n" +
            "By using our site, you confirm that you accept these terms and conditions and that you agree to comply with them.\n" +
            "\n" +
            "If you do not agree to these terms and conditions, you must not use our site.\n" +
            "Other Applicable Terms\n" +
            "\n" +
            "These terms and conditions refer to the following additional terms, which also apply to your use of our site:\n" +
            "\n" +
            "    Our Privacy Policy, which sets out the terms on which we process any personal data we collect from you, or that you provide to us. By using our site, you consent to such processing and you warrant that all data provided by you is accurate.\n" +
            "    Our Acceptable Use Policy, which sets out the permitted uses and prohibited uses of our site. When using our site, you must comply with this Acceptable Use Policy.\n" +
            "    Our Cookie Policy, which sets out information about the cookies on our site.\n" +
            "\n" +
            "If you purchase services from our site, our Terms and Conditions for the Supply of Services will apply to the sales.\n" +
            "Information About Us\n" +
            "\n" +
            "https://openexchangerates.org is a site operated by Open Exchange Rates (UK) Limited (\"We\" or \"Us\"). We are registered in England under company number 10516590 and have our registered office at 5 Luke Street, London, England EC2A 4PX. We are a limited company.\n" +
            "Changes To These Terms\n" +
            "\n" +
            "We may revise these terms and conditions at any time by amending this page.\n" +
            "\n" +
            "Please check this page from time to time to take notice of any changes we made, as they are binding on you. We will also post a notice on our website when we revise these terms and conditions.\n" +
            "Changes To Our Site\n" +
            "\n" +
            "We may update our site from time to time, and may change the content at any time. However, please note that any of the content on our site may be out of date at any given time, and we are under no obligation to update it.\n" +
            "\n" +
            "We do not guarantee that our site, or any content on it, will be free from errors or omissions.\n" +
            "Accessing Our Site\n" +
            "\n" +
            "We do not guarantee that our site, or any content on it, will always be available or be uninterrupted. Access to our site is permitted on a temporary basis. We may suspend, withdraw, discontinue or change all or any part of our site without notice. We will use reasonable endeavours to give 30 days notice of any such suspension of service. We will not be liable to you if for any reason our site is unavailable at any time or for any period.\n" +
            "\n" +
            "You are responsible for making all arrangements necessary for you to have access to our site.\n" +
            "\n" +
            "You are also responsible for ensuring that all persons who access our site through your Internet connection are aware of these terms and conditions and other applicable terms and conditions, and that they comply with them.\n" +
            "Your Account And Password\n" +
            "\n" +
            "If you choose, or you are provided with, a user identification code, password or any other piece of information as part of our security procedures, you must treat such information as confidential. You must not disclose it to any third party.\n" +
            "\n" +
            "We have the right to disable any user identification code or password, whether chosen by you or allocated by us, or to restrict or prevent access from any IP address, at any time, if in our reasonable opinion you have failed to comply with any of the provisions of these terms and conditions.\n" +
            "\n" +
            "If you know or suspect that anyone other than you knows your user identification code or password, you must promptly notify us at support@openexchangerates.org.\n" +
            "Intellectual Property Rights\n" +
            "\n" +
            "We are the owner or the licensee of all intellectual property rights in our site, and in the material published on it. Those works are protected by copyright laws and treaties around the world. All such rights are reserved.\n" +
            "\n" +
            "Subject to your compliance with these terms and conditions and the other applicable terms, we grant you a non-transferable, non-exclusive, revocable, limited right to use the information on our site.\n" +
            "\n" +
            "You must not profit directly from the sale or provision of information obtained on or through our site or by re-selling access to our site, without obtaining a licence to do so from us.\n" +
            "\n" +
            "If you copy or download any part of our site in breach of these terms and conditions, your right to use our site will cease immediately and you must, at our option, return or destroy any copies of the materials you have made.\n" +
            "No Reliance On Information\n" +
            "\n" +
            "The content on our site is provided for general information only. It is not intended to amount to advice on which you should rely. You must obtain professional or specialist advice before taking, or refraining from, any action on the basis of the content on our site.\n" +
            "\n" +
            "Although we make reasonable efforts to update the information on our site, we make no representations, warranties or guarantees, whether express or implied, that the content on our site or made available through our site is accurate, complete or up-to-date.\n" +
            "Limitation Of Our Liability\n" +
            "\n" +
            "Nothing in these terms and conditions excludes or limits our liability for death or personal injury arising from our negligence, or our fraud or fraudulent misrepresentation, or any other liability that cannot be excluded or limited by English law.\n" +
            "\n" +
            "To the extent permitted by law, we exclude all conditions, warranties, representations or other terms which may apply to our site or any content on it, whether express or implied.\n" +
            "\n" +
            "We will not be liable to any user for any loss or damage, whether in contract, tort (including negligence), breach of statutory duty, or otherwise, even if foreseeable, arising under or in connection with:\n" +
            "\n" +
            "    use of, or inability to use, our site; or\n" +
            "    use of or reliance on any content displayed on our site.\n" +
            "\n" +
            "If you are a business user, please note that in particular, we will not be liable for:\n" +
            "\n" +
            "    loss of profits, sales, business, or revenue;\n" +
            "    business interruption;\n" +
            "    loss of anticipated savings;\n" +
            "    loss of business opportunity, goodwill or reputation; or\n" +
            "    any indirect or consequential loss or damage.\n" +
            "\n" +
            "If you are a consumer user, please note that we only provide our site for domestic and private use. You agree not to use our site for any commercial or business purposes, and we have no liability to you for any loss of profit, loss of business, business interruption, or loss of business opportunity.\n" +
            "\n" +
            "We will not be liable for any loss or damage caused by a virus, distributed denial-of-service attack, or other technologically harmful material that may infect your computer equipment, computer programs, data or other proprietary material due to your use of our site or to your downloading of any content on it, or on any website linked to it.\n" +
            "\n" +
            "We assume no responsibility for the content of websites linked on our site. Such links should not be interpreted as endorsement by us of those linked websites. We will not be liable for any loss or damage that may arise from your use of them.\n" +
            "Uploading Content To Our Site\n" +
            "\n" +
            "Whenever you make use of a feature that allows you to upload content to our site (or for us to upload content on your behalf), or to make contact with other users of our site, you must comply with the content standards set out in our Acceptable Use Policy.\n" +
            "\n" +
            "You warrant that any such contribution does comply with those standards, and you will be liable to us and indemnify us for any breach of that warranty. If you are a consumer user, this means you will be responsible for any loss or damage we suffer as a result of your breach of warranty.\n" +
            "\n" +
            "Any content you upload to our site will be considered non-confidential and non-proprietary, and we have the right to use, copy, distribute and disclose to third parties any such content for any purpose.\n" +
            "\n" +
            "We also have the right to disclose your identity to any third party who is claiming that any content posted or uploaded by you to our site constitutes a violation of their intellectual property rights, or of their right to privacy.\n" +
            "\n" +
            "We will not be responsible, or liable to any third party, for the content or accuracy of any content posted by you or any other user of our site.\n" +
            "\n" +
            "We have the right to remove any posting you make on our site if, in our opinion, your post does not comply with the content standards set out in our Acceptable Use Policy.\n" +
            "\n" +
            "The views expressed by other users on our site do not represent our views or values.\n" +
            "Viruses\n" +
            "\n" +
            "We do not guarantee that our site will be secure or free from bugs or viruses.\n" +
            "\n" +
            "You are responsible for configuring your information technology, computer programmes and platform in order to access our site. You should use your own virus protection software.\n" +
            "\n" +
            "You must not misuse our site by knowingly introducing viruses, trojans, worms, logic bombs or other material which is malicious or technologically harmful. You must not attempt to gain unauthorised access to our site, the server on which our site is stored or any server, computer or database connected to our site. You must not attack our site via a denial-of-service attack or a distributed denial-of service attack. By breaching this provision, you would commit a criminal offence under the Computer Misuse Act 1990. We will report any such breach to the relevant law enforcement authorities and we will co-operate with those authorities by disclosing your identity to them. In the event of such a breach, your right to use our site will cease immediately.\n" +
            "Linking To Our Site\n" +
            "\n" +
            "You may link to our website, provided you do so in a way that is fair and legal and does not damage our reputation or take advantage of it.\n" +
            "\n" +
            "You must not establish a link in such a way as to suggest any form of association, approval or endorsement on our part where none exists.\n" +
            "\n" +
            "You must not establish a link to our site in any website that is not owned by you without permission from us to do so.\n" +
            "\n" +
            "Our site must not be framed (displayed or loaded) on any other site.\n" +
            "\n" +
            "We reserve the right to withdraw linking permission without notice.\n" +
            "\n" +
            "The website in which you are linking must comply in all respects with the content standards set out in our Acceptable Use Policy.\n" +
            "\n" +
            "If you wish to make any use of content on our site other than that set out above, please contact support@openexchangerates.org.\n" +
            "Third-Party Links And Resources In Our Site\n" +
            "\n" +
            "Where our site contains links to other sites and resources provided by third parties, these links are provided for your information only.\n" +
            "\n" +
            "We have no control over the contents of those sites or resources and do not endorse or suggest any affiliation with them.\n" +
            "Applicable Law\n" +
            "\n" +
            "If you are a consumer, please note that these terms and conditions, its subject matter and its formation, are governed by English law. You and we both agree to that the courts of England and Wales will have non-exclusive jurisdiction. However, if you are a resident of Northern Ireland you may also bring proceedings in Northern Ireland, and if you are resident of Scotland, you may also bring proceedings in Scotland.\n" +
            "\n" +
            "If you are a business, these terms and conditions, its subject matter and its formation (and any non-contractual disputes or claims) are governed by English law. We both agree to the exclusive jurisdiction of the courts of England and Wales.\n" +
            "Trade Marks\n" +
            "\n" +
            "\"Open Exchange Rates\" and \"openexchangerates.org\" are trade marks of Open Exchange Rates Limited (incorporated and registered in Jersey with company number 112541 whose registered office is at First Floor, Tower House, La Route Es Nouaux, St Helier Jersey JE2 4ZJ) and are used by us under exclusive worldwide license.\n" +
            "Entire Agreement\n" +
            "\n" +
            "These Terms and Conditions of Website Use and the documents referred to herein (agreement) constitute the entire agreement between the parties and supersede and extinguish all previous agreements, promises, assurances, warranties, representations and understandings between them, whether written or oral, relating to their subject matter.\n" +
            "\n" +
            "Each party acknowledges that in entering into this agreement it does not rely on, and shall have no remedies in respect of, any statement, representation, assurance or warranty (whether made innocently or negligently) that is not set out in this agreement.\n" +
            "\n" +
            "Each party agrees that it shall have no claim for innocent or negligent misrepresentation or negligent misstatement based on any statement in this agreement.\n";

}