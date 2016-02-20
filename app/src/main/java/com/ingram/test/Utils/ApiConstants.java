package com.ingram.test.Utils;

/**
 * Created by vijayarajsekar on 19/2/16.
 */

public interface ApiConstants {

    public static String PREFERENCE_NAME = "FootballData";

    public static int REQ_DATA_URL = 1;
    public static int REQ_TEAM_DETAILS = 2;

    public static int REQ_PLAYER_DETAILS = 3;

    public static String DATA_URL = "http://api.football-data.org/alpha/soccerseasons/398/fixtures";

    public static String FIXTURES = "fixtures";

    public static String LINKS = "_links";

    public static String LINKS_SOCCERSEASON = "soccerseason";
    public static String LINKS_HOMETEAM = "homeTeam";
    public static String LINKS_AWAYTEAM = "awayTeam";

    public static String TEAM_DETAILS_NAME = "name";
    public static String TEAM_DETAILS_CODE = "code";
    public static String TEAM_DETAILS_SHORTNAME = "shortName";
    public static String TEAM_DETAILS_SQUADMARKETVALUE = "squadMarketValue";
    public static String TEAM_DETAILS_CRESTURL = "crestUrl";

    public static String LINKS_SELF = "self";
    public static String LINKS_HREF = "href";

    public static String LINKS_PLAYERS = "players";

    public static String PLAYER_ID = "id";
    public static String PLAYER_NAME = "name";
    public static String PLAYER_NATIONALITY = "nationality";

    public static String DATE = "date";
    public static String STATUS = "status";
    public static String MATCHDAY = "matchday";
    public static String HOMETEAM_NAME = "homeTeamName";
    public static String AWAYTEAM_NAME = "awayTeamName";

    public static String RESULT = "result";


    public static String RESULT_GOALS_HOMETEAM = "goalsHomeTeam";
    public static String RESULT_GOALS_AWAYTEAM = "goalsAwayTeam";


    /*
    Preference String
     */

    public String mIsReadOnline = "IsReadOnline";
    public String mMatchDetails = "MatchDetails";
}
