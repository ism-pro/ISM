/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.services.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.ism.services.Util;
import org.ism.entities.smq.nc.NonConformiteRequest;
import org.ism.util.DateUtil;

/**
 * Mail class provide the way to save informations of email to be send.<br>
 *
 * It is taking care of destination
 *
 * @author r.hendrick
 *
 */
public class Mail {

    /**
     * List of receivers email
     */
    private List<String> tos = new ArrayList<>();
    /**
     * List of receivers copy email
     */
    private List<String> ccs = new ArrayList<>();
    /**
     * List of receivers hiddens copy email
     */
    private List<String> ccis = new ArrayList<>();

    /**
     * Special composition email containing only text which is prefered message
     */
    private String htmlText = null;

    /**
     * Special composition email containing images come after htmlText in
     * managing order
     */
    private MimeMultipart htmlMultipart = null;

    /**
     * Subject email message which is empty string by default
     */
    private String subject = "";

    /**
     * Specify smtp server with default value empty string
     */
    private String smtp = ""; //smtp.gmail.com";

    /**
     * Specified default port which is 25 by default
     */
    private int port = 25;

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Constructors
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Default constructor Mail
     */
    public Mail() {
    }

    /**
     * Basic Mail constructor
     *
     * @param receiverEmailAddress email receiver
     * @param subject of the email
     * @param htmlText is a html string message
     */
    public Mail(String receiverEmailAddress, String subject, String htmlText) {
        tos.add(receiverEmailAddress.replace(";", ",").trim());
        this.subject = subject;
        this.htmlText = htmlText;
    }

    public Mail(String receiverEmailAddress, String subject, MimeMultipart htmlMultipart) {
        tos.add(receiverEmailAddress.replace(";", ",").trim());
        this.subject = subject;
        this.htmlMultipart = htmlMultipart;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Container
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    /**
     * add To allow to insert new email address mailAddressOfTo to the current
     * list of receiver email
     *
     * @param mailAddressOfTo one email address
     */
    public void addTo(String mailAddressOfTo) {
        if (tos == null) {
            tos = new ArrayList<>();
        }
        tos.add(mailAddressOfTo.replace(";", ",").trim());
    }

    /**
     * add To allow to insert new email address mailAddressOfTo to the current
     * list of receiver email
     *
     * @param mailAddressTos multiple email addresses
     */
    public void addTos(List<String> mailAddressTos) {
        if (tos == null) {
            tos = new ArrayList<>();
        }
        tos.addAll(mailAddressTos);
    }

    /**
     * add CC allow to insert new email address mailAddressOfCarbonCopy to the
     * current list of receiver email
     *
     * @param mailAddressOfCarbonCopy one email address
     */
    public void addCC(String mailAddressOfCarbonCopy) {
        if (ccs == null) {
            ccs = new ArrayList<>();
        }
        ccs.add(mailAddressOfCarbonCopy.replace(";", ",").trim());
    }

    /**
     * add CC allow to insert new email address mailAddressOfCarbonCopy to the
     * current list of receiver email
     *
     * @param mailAddressOfCarbonCopys multiple email address
     */
    public void addCCs(List<String> mailAddressOfCarbonCopys) {
        if (ccs == null) {
            ccs = new ArrayList<>();
        }
        ccs.addAll(mailAddressOfCarbonCopys);
    }

    /**
     * add CC allow to insert new email address mailAddressOfCarbonCopyInvisible
     * to the current list of receiver email
     *
     * @param mailAddressOfCarbonCopyInvisible one email address
     */
    public void addCCI(String mailAddressOfCarbonCopyInvisible) {
        if (ccs == null) {
            ccs = new ArrayList<>();
        }
        ccis.add(mailAddressOfCarbonCopyInvisible.replace(";", ",").trim());
    }

    /**
     * add CCIs allow to insert new email addresses
     * mailAddressOfCarbonCopyInvisibles to the current list of receiver email
     *
     * @param mailAddressOfCarbonCopyInvisibles multiple emails addresses
     */
    public void addCCIs(String mailAddressOfCarbonCopyInvisibles) {
        if (ccs == null) {
            ccs = new ArrayList<>();
        }
        ccis.add(mailAddressOfCarbonCopyInvisibles);
    }

    /**
     *
     * @return
     */
    public Multipart getContent() {
        if (htmlText != null) {
            try {
                // This mail has 2 part, the BODY and the embedded image
                MimeMultipart multipart = new MimeMultipart("related");

                // first part (the html)
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlText, "text/html");
                // add it
                multipart.addBodyPart(messageBodyPart);
                return multipart;
            } catch (MessagingException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (htmlMultipart != null) {
            return htmlMultipart;
        }

        return null;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Getter / Setter
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    public List<String> getTos() {
        return tos;
    }

    public void setTos(List<String> tos) {
        this.tos = tos;
    }

    public List<String> getCcs() {
        return ccs;
    }

    public void setCcs(List<String> ccs) {
        this.ccs = ccs;
    }

    public List<String> getCcis() {
        return ccis;
    }

    public void setCcis(List<String> ccis) {
        this.ccis = ccis;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public MimeMultipart getHtmlMultipart() {
        return htmlMultipart;
    }

    public void setHtmlMultipart(MimeMultipart htmlMultipart) {
        this.htmlMultipart = htmlMultipart;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    // /////////////////////////////////////////////////////////////////////////
    //
    //
    // Container
    //
    //
    // /////////////////////////////////////////////////////////////////////////
    public static MimeMultipart msgTest() {
        try {
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = msgStandard();
            htmlText = htmlText.replace("%Title%", "Message de Test")
                    .replace("%Content%", "La messagerie est correctement configurée");
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(Util.writeResourceToFileSystem("img/ism/ism.png"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            return multipart;
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static MimeMultipart createMessage(String title, String content) {
        try {
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = msgStandard();
            htmlText = htmlText.replace("%Title%", title)
                    .replace("%Content%", content);
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(Util.writeResourceToFileSystem("img/ism/ism.png"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            return multipart;
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static MimeMultipart createMessageNC(String title, NonConformiteRequest nc) {
        try {
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = msgNonConformite(nc.getNcrState().getId());
            htmlText = htmlText.replace("%Title%", title)
                    .replace("%nc_refusDesc%", (nc.getNcrapprouvedDesc()== null ? "" : nc.getNcrapprouvedDesc()))
                    .replace("%nc_id%", nc.getNcrId().toString()).replace("%nc_company%", nc.getNcrCompany().toString())
                    .replace("%nc_Processus", nc.getNcrProcessus().toString()).replace("%nc_nature%", nc.getNcrNature().toString())
                    .replace("%nc_gravity%", nc.getNcrGravity().toString()).replace("%nc_frequency%", nc.getNcrFrequency().toString())
                    .replace("%nc_occured%", DateUtil.format("dd/MM/yyyy hh:mm:ss", nc.getNcrOccured())).replace("%nc_product%", (nc.getNcrProduct() == null ? "" : nc.getNcrProduct()))
                    .replace("%nc_trace%", (nc.getNcrTrace() == null ? "" : nc.getNcrTrace())).replace("%nc_quantity%", (nc.getNcrQuantity() == null ? "" : nc.getNcrQuantity().toString()))
                    .replace("%nc_unit%", (nc.getNcrUnite() == null ? "" : nc.getNcrUnite().toString()))
                    .replace("%nc_clientname%", (nc.getNcrClientname() == null ? "" : nc.getNcrClientname())).replace("%nc_clientaddress%", (nc.getNcrClientaddress() == null ? "" : nc.getNcrClientaddress()))
                    .replace("%nc_clientphone%", (nc.getNcrClientphone() == null ? "" : nc.getNcrClientphone())).replace("%nc_clientmail%", (nc.getNcrClientemail() == null ? "" : nc.getNcrClientemail()))
                    .replace("%nc_clienttype%", (nc.getNcrClienttype() == null ? "" : nc.getNcrClienttype()))
                    .replace("%nc_emettor", nc.getNcrStaff().toString()).replace("%nc_created%", DateUtil.format("dd/MM/yyyy hh:mm:ss", nc.getNcrCreated()))
                    .replace("%nc_description%", nc.getNcrDescription()).replace("%nc_image%", (nc.getNcrLink() == null ? "" : nc.getNcrLink()));
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(Util.writeResourceToFileSystem("img/ism/ism.png"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            return multipart;
        } catch (MessagingException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String msgStandard() {
        return ""
                + "<head>\n"
                + "<title>ISM MESSAGER</title>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "<style>\n"
                + "	.bodyText {\n"
                + "	font:11px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color:#003366;\n"
                + "	line-height:20px;\n"
                + "	margin-top:0px;\n"
                + "	}\n"
                + "	.pageName{\n"
                + "	font: 18px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color: #3366CC;\n"
                + "	line-height:24px;\n"
                + "	letter-spacing:.2em;\n"
                + "	}\n"
                + "	.signature{ margin-top: 30px; }\n"
                + "	.sign_table{ border-top: 1px solid #BD2B11; border-bottom: 1px solid #BD2B11; }\n"
                + "	.sign_name	{\n"
                + "	font:24px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color: #3366CC;\n"
                + "	letter-spacing:.2em;\n"
                + "	line-height:30px;\n"
                + "	}\n"
                + "	.sign_complement{\n"
                + "	font:12px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color: #FF9933;\n"
                + "	letter-spacing:.4em;\n"
                + "	line-height:18px;\n"
                + "	}\n"
                + "\n"
                + "</style>\n"
                + "</head>\n"
                + "<body >\n"
                + "	<h1 class=\"pageName\" >%Title%</h1>\n"
                + "    \n"
                + "    <div class=\"bodyText\">%Content%</div>\n"
                + "    \n"
                + "    <div class=\"signature\">\n"
                + "    	<table width=\"510\" class=\"sign_table\">\n"
                + "        <tr><td width=\"179\" rowspan=\"3\"><img src=\"cid:image\" alt=\"Header image\" width=\"179\" height=\"68\" border=\"0\" /></td>\n"
                + "        <td width=\"329\" class=\"sign_name\">ISM MESSAGER</td>\n"
                + "        </tr>         <tr><td class=\"sign_complement\">RESTER INFORMER</td></tr>\n"
                + "        <tr><td class=\"sign_complement\">Industrial System Manager</td></tr>\n"
                + "        </table>\n"
                + "</div>\n"
                + "</body>";
    }

    /**
     * Allow to create a message for non conformite
     *
     * @param code <br>
     * <br> 1	A	Créée
     * <br>2	B	En attente de solution
     * <br>3	C	En cours
     * <br>4	D	Terminé
     * <br>5	E	Annulé
     *
     * @return
     */
    public static String msgNonConformite(Integer code) {
        String state = "";
        switch (code) {
            case 1:
                state = "créée, merci de la traiter...";
                break;
            case 5:
                state = "refusée !";
                break;
            case 2:
                state = "validée, merci d'approter une actions...";
                break;
            case 3:
                state = "en cours";
                break;
            default:
                state = "???";
                break;
        }
        return ""
                + "<head>\n"
                + "<title>ISM MESSAGER</title>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "<style>\n"
                + "	.bodyText {\n"
                + "	font:11px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color:#003366;\n"
                + "	line-height:20px;\n"
                + "	margin-top:0px;\n"
                + "	}\n"
                + "	.pageName{\n"
                + "	font: 18px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color: #3366CC;\n"
                + "	line-height:24px;\n"
                + "	letter-spacing:.2em;\n"
                + "	}\n"
                + "	.signature{ margin-top: 30px; }\n"
                + "	.sign_table{ border-top: 1px solid #BD2B11; border-bottom: 1px solid #BD2B11; }\n"
                + "	.sign_name	{\n"
                + "	font:24px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color: #3366CC;\n"
                + "	letter-spacing:.2em;\n"
                + "	line-height:30px;\n"
                + "	}\n"
                + "	.sign_complement{\n"
                + "	font:12px Verdana, Arial, Helvetica, sans-serif;\n"
                + "	color: #FF9933;\n"
                + "	letter-spacing:.4em;\n"
                + "	line-height:18px;\n"
                + "	}\n"
                + "\n"
                + "	.nc_table {  width: 100%;  border-collapse:collapse; font:11px Verdana, Arial, Helvetica, sans-serif; color:#003366;}\n"
                + "	.nc_table td  { border: 1px solid #EEEEEE; }\n"
                + "	.nc_table_field { width : 75px; }\n"
                + "</style>\n"
                + "</head>\n"
                + "<body >\n"
                + "	<h1 class=\"pageName\" >%Title%</h1>\n"
                + "    \n"
                + "    <div class=\"bodyText\">\n"
                + "      <p>Bonjour,</p>\n"
                + "      <p>Cette non-conformité vient d'être <span style=\"color: #FF6600; font-weight:bold;\">" + state + "</span> <br/>Prennez en connaissance ci-dessous :      </p>\n"
                + "      <div style=\"width: 100%; color: red;border: 1px dashed red;margin: 10px;padding: 10px;  " + (code != 5 ? "display:none;" : "") + "\"><p>%nc_refusDesc%</p></div>"
                + "      <h3>Informations</h3>\n"
                + "   	  <table border=\"0\" cellpadding=\"0\"  cellspacing=\"0\" class=\"nc_table\">\n"
                + "        <tr><td class=\"nc_table_field\">N°</td><td>%nc_id%</td></tr>\n"
                + "        <tr><td>Société</td><td>%nc_company%</td></tr>\n"
                + "        <tr><td>Processus</td><td>%nc_processus%</td></tr>\n"
                + "        <tr><td>Nature</td><td>%nc_nature%</td></tr>\n"
                + "        <tr><td>Gravité</td><td>%nc_gravity%</td></tr>\n"
                + "        <tr><td>Fréquence</td><td>%nc_frequency%</td></tr>\n"
                + "        <tr><td>Apparution</td><td>%nc_occured%</td></tr>\n"
                + "        <tr><td>Produit</td><td>%nc_product%</td></tr>\n"
                + "        <tr><td>Traçabilité</td><td>%nc%trace%</td></tr>\n"
                + "        <tr><td>Quantité</td><td>%nc_quantity%</td></tr>\n"
                + "        <tr><td>Unité</td><td>%nc_unit%</td></tr>\n"
                + "        </table>\n"
                + "        <h3>Infos Client</h3>\n"
                + "        <table class=\"nc_table\">\n"
                + "        <tr><td class=\"nc_table_field\">Nom</td><td>%nc_clientname%</td></tr>\n"
                + "        <tr><td>Adresse</td><td>%nc_clientaddress%</td></tr>\n"
                + "        <tr><td>Téléphone</td><td>%nc_clientphone%</td></tr>\n"
                + "        <tr><td>E-mail</td><td>%nc_clientmail%</td></tr>\n"
                + "        <tr><td>Type</td><td>%nc_clienttype%</td></tr>\n"
                + "        </table>\n"
                + "        <h3>Description</h3>\n"
                + "        <table class=\"nc_table\">\n"
                + "        <tr><td class=\"nc_table_field\">Emetteur</td><td>%nc_emettor</td></tr>\n"
                + "        <tr><td>Création</td><td>%nc_created%</td></tr>\n"
                + "        <tr><td>Description</td><td>%nc_description%</td></tr>\n"
                + "        <tr><td>Image</td><td>%nc_image%</td></tr>\n"
                + "        </table>\n"
                + "      </p>\n"
                + "    </div>\n"
                + "    \n"
                + "    <div class=\"signature\">\n"
                + "    	<table width=\"510\" class=\"sign_table\">\n"
                + "        <tr><td width=\"179\" rowspan=\"4\"><img src=\"cid:image\" alt=\"Header image\" width=\"179\" height=\"68\" border=\"0\" /></td>\n"
                + "        <td width=\"329\" class=\"sign_name\">ISM MESSAGER</td>\n"
                + "        </tr>         <tr><td class=\"sign_complement\">RESTER INFORMER</td></tr>\n"
                + "        <tr><td class=\"sign_complement\">Industrial System Manager</td></tr>\n"
                + "        </table>\n"
                + "</div>\n"
                + "</body>";
    }
}
