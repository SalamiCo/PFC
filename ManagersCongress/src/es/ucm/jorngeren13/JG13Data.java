package es.ucm.jorngeren13;

import android.database.Cursor;
import android.database.MatrixCursor;

public final class JG13Data {

    private static long index = 0;
    private static MatrixCursor agendaCursor;
    private static MatrixCursor speakersCursor;

    public static Cursor getAgendaCursor () {
        if (agendaCursor == null) {
            agendaCursor = new MatrixCursor(new String[] { "_id", "title", "description", "day", "start_time" });

            final String STR_NOV_11 = "11 de Noviembre";
            final String STR_NOV_12 = "12 de Noviembre";
            final String STR_NOV_13 = "13 de Noviembre";
            
            // First day
            agendaRow(
                "Recepción de los asistentes y entrega de documentación y acreditaciones.", null, STR_NOV_11, "12:00");
            agendaRow("Inauguración de las XXXI Jornadas de Gerencia Universitaria.", null, STR_NOV_11, "13:00");
            agendaRow(
                "Conferencia: Reinventando la toma de decisiones en la Universidad.",
                "José Ramón Chaves García. Magistrado de lo contencioso-administrativo.", STR_NOV_11, "13:15");
            agendaRow("Cóctel en el Paraninfo.", null, STR_NOV_11, "14:15");
            agendaRow(
                "Constitución de los grupos de trabajo en librerías emblemáticas de Madrid: La Central, Ocho y Medio y Tipos Infames.",
                null, STR_NOV_11, "17:00");
            agendaRow("Cóctel en el Palacio de Cibeles (sede del Ayuntamiento de Madrid)", null, STR_NOV_11, "21:30");

            // Second day
            agendaRow(
                "Mesa redonda: Reducción de costes y optimización de recursos.",
                "Moderador: Teodoro Conde Minaya. Gerente UAM.", STR_NOV_12, "09:00");
            agendaRow(" Pausa-café.", null, STR_NOV_12, "11:00");
            agendaRow("Talleres.", null, STR_NOV_12, "11:30");
            agendaRow(
                "Mesa redonda: Alternativas a la financiación pública y captación de fondos.",
                "Moderador: Jordi Montserrat Garrocho. Gerente UNED.", STR_NOV_12, "12:30");
            agendaRow("Almuerzo en el Museo del Traje.", null, STR_NOV_12, "14:30");
            agendaRow(
                "Conferencia: Gerencia en la Universidad: Visión en Acción.", "Javier Oliva López", STR_NOV_12, "16:30");
            agendaRow("Mesa de gerentes.", null, STR_NOV_12, "17:30");
            agendaRow("Cena institucional.", null, STR_NOV_12, "21:30");

            // Third day
            agendaRow(
                "Conferencia: \"Educación universitaria y Fundaciones americanas\"",
                "Eelco Keij. Especialista en fundraising. Fundador de KeyLance Consultancy LLC en Nueva York.",
                STR_NOV_13, "09:30");
            agendaRow("Talleres.", null, STR_NOV_13, "10:30");
            agendaRow("Pausa-café.", null, STR_NOV_13, "11:30");
            agendaRow(
                "Mesa redonda: Gestión de personas en tiempos de crisis. Nuevos enfoques en la gestión de los recursos humanos.",
                "Moderadora: Carmen García Elias. Gerente UPM", STR_NOV_13, "12:00");
            agendaRow("Clausura de las jornadas.", null, STR_NOV_13, "14:00");
            agendaRow("Cóctel de despedida.", null, STR_NOV_13, "14:15");
        }

        return agendaCursor;
    }

    private static void agendaRow (String title, String subtitle, String date, String time) {
        agendaCursor.addRow(new Object[] { ++index, title, subtitle, date, time });
    }

    public static Cursor getSpeakersCursor () {
        if (speakersCursor == null) {
            speakersCursor = new MatrixCursor(new String[] { "_id", "name", "position", "picture" });

            speakerRow(
                "Jose Ramón Chaves García", "Magistrado de lo Contencioso-Administrativo", R.drawable.speaker_chaves);
            speakerRow("Teodoro Conde Minaya", "Gerente de la UAM", R.drawable.speaker_conde_minaya);
            speakerRow("Carmen García Elías", "Gerente de la UPM", R.drawable.speaker_garcia_elias);
            speakerRow(
                "Eelco Keij", "Especialista en fundraising. Fundador de KeyLance Consultancy LLC en Nueva York",
                R.drawable.speaker_keij);
            speakerRow("Jordi Montserrat Garrocho", "Gerente de la UNED", R.drawable.speaker_montserrat);
            speakerRow(
                "Javier Oliva Jópez",
                "Licenciado en Ciencias Económicas y Empresariales en la especialidad de Dirección y Gestión de empresas",
                R.drawable.speaker_oliva);
        }

        return speakersCursor;
    }

    private static void speakerRow (String name, String position, int picture) {
        speakersCursor.addRow(new Object[] { ++index, name, position, picture });
    }

    private JG13Data () {
        throw new AssertionError("static class");
    }
}
