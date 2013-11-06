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

            // First day
            agendaRow(
                "Recepción de los asistentes y entrega de documentación y acreditaciones.", null, "11 Nov", "12:00");
            agendaRow("Inauguración de las XXXI Jornadas de Gerencia Universitaria.", null, "11 Nov", "13:00");
            agendaRow(
                "Conferencia: Reinventando la toma de decisiones en la Universidad.",
                "José Ramón Chaves García. Magistrado de lo contencioso-administrativo.", "11 Nov", "13:15");
            agendaRow("Cóctel en el Paraninfo.", null, "11 Nov", "14:15");
            agendaRow(
                "Constitución de los grupos de trabajo en librerías emblemáticas de Madrid: La Central, Ocho y Medio y Tipos Infames.",
                null, "11 Nov", "17:00");
            agendaRow("Cóctel en el Palacio de Cibeles (sede del Ayuntamiento de Madrid)", null, "11 Nov", "21:30");

            // Second day
            agendaRow(
                "Mesa redonda: Reducción de costes y optimización de recursos.",
                "Moderador: Teodoro Conde Minaya. Gerente UAM.", "12 Nov", "09:00");
            agendaRow(" Pausa-café.", null, "12 Nov", "11:00");
            agendaRow("Talleres.", null, "12 Nov", "11:30");
            agendaRow(
                "Mesa redonda: Alternativas a la financiación pública y captación de fondos.",
                "Moderador: Jordi Montserrat Garrocho. Gerente UNED.", "12 Nov", "12:30");
            agendaRow("Almuerzo en el Museo del Traje.", null, "12 Nov", "14:30");
            agendaRow(
                "Conferencia: Gerencia en la Universidad: Visión en Acción.", "Javier Oliva López", "12 Nov", "16:30");
            agendaRow("Mesa de gerentes.", null, "12 Nov", "17:30");
            agendaRow("Cena institucional.", null, "12 Nov", "21:30");

            // Third day
            agendaRow(
                "Conferencia: \"Educación universitaria y Fundaciones americanas\"",
                "Eelco Keij. Especialista en fundraising. Fundador de KeyLance Consultancy LLC en Nueva York.",
                "13 Nov", "09:30");
            agendaRow("Talleres.", null, "13 Nov", "10:30");
            agendaRow("Pausa-café.", null, "13 Nov", "11:30");
            agendaRow(
                "Mesa redonda: Gestión de personas en tiempos de crisis. Nuevos enfoques en la gestión de los recursos humanos.",
                "Moderadora: Carmen García Elias. Gerente UPM", "13 Nov", "12:00");
            agendaRow("Clausura de las jornadas.", null, "13 Nov", "14:00");
            agendaRow("Cóctel de despedida.", null, "13 Nov", "14:15");
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
