/**
 * (c) 2003-2014 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.module.facebook; 

import org.junit.Test;
import org.mockito.Mockito;

public class FacebookConnectorListsUnitTest extends FacebookConnectorGenericUnitTest
{
    public FacebookConnectorListsUnitTest()
    {
        super("{\"data\":[{\"id\":\"769398592_10151145541863593\",\"from\":{\"name\":\"Bernardo Jeannot\",\"id\":\"769398592\"},\"message\":\"PRIMERA RONDA\\n1. Temperley (V)\\n2. San Carlos (L)\\n3. Almagro (V)\\n4. Central C\u00f3rdoba (L)\\n5. Atlanta (V)\\n6. Platense (L)\\n7. Comunicaciones (V)\\n8. Trist\u00e1n Su\u00e1rez (L)\\n9. Mor\u00f3n (V)\\n10. Acassuso (L)\\n11. Los Andes (V)\\n12. San Telmo (L)\\n13. Estudiantes (V)\\n14. Villa D\u00e1lmine (L)\\n15. Defensores de Belgrano (V)\\n16. Chacarita (L)\\n17. Barracas Central (V)\\n18. Brown de Adrogu\u00e9 (L)\\n19. Libre\\n20. Colegiales (V)\\n21. Armenio (L)\",\"type\":\"status\",\"created_time\":\"2012-07-24T12:43:23+0000\",\"updated_time\":\"2012-07-24T12:43:23+0000\"}],\"paging\":{\"previous\":\"https:\\/\\/graph.facebook.com\\/search?q=chacarita&limit=1&since=1343133803&type=post&value=1&__previous=1\",\"next\":\"https:\\/\\/graph.facebook.com\\/search?q=chacarita&limit=1&type=post&value=1&until=1343133802\"}}");
    }
    
    @Test
    public void testSearchPosts()
    {
        connector.searchPosts("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetAlbumPhotos()
    {
        connector.getAlbumPhotos("test", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testSearchUsers()
    {
        connector.searchUsers("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testSearchCheckinks()
    {
        connector.searchCheckins("", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testSearchEvents()
    {
        connector.searchEvents("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testSearchPages()
    {
        connector.searchPages("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testSearchGroups()
    {
        connector.searchGroups("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetAlbumComments()
    {
        connector.getAlbumComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetEventWall()
    {
        connector.getEventWall("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetEventNoReply()
    {
        connector.getEventNoReply("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetEventMaybe()
    {
        connector.getEventMaybe("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetEventInvited()
    {
        connector.getEventInvited("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetEventAttending()
    {
        connector.getEventAttending("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetEventDeclined()
    {
        connector.getEventDeclined("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetGroupWall()
    {
        connector.getGroupWall("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetGroupMembers()
    {
        connector.getGroupMembers("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetLinkComments()
    {
        connector.getLinkComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetNoteComments()
    {
        connector.getNoteComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageWall()
    {
        connector.getPageWall("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageTagged()
    {
        connector.getPageTagged("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageLinks()
    {
        connector.getPageLinks("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPagePhotos()
    {
        connector.getPagePhotos("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageGroups()
    {
        connector.getPageGroups("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageAlbums()
    {
        connector.getPageAlbums("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageStatuses()
    {
        connector.getPageStatuses("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageVideos()
    {
        connector.getPageVideos("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageNotes()
    {
        connector.getPageNotes("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPagePosts()
    {
        connector.getPagePosts("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageEvents()
    {
        connector.getPageEvents("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPageCheckins()
    {
        connector.getPageCheckins("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPhotoComments()
    {
        connector.getPhotoComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetPostComments()
    {
        connector.getPostComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetStatusComments()
    {
        connector.getStatusComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserSearch()
    {
        connector.getUserSearch("", "", "", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserHome()
    {
        connector.getUserHome("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserWall()
    {
        connector.getUserWall("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserTagged()
    {
        connector.getUserTagged("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserPosts()
    {
        connector.getUserPosts("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserFriends()
    {
        connector.getUserFriends("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserActivities()
    {
        connector.getUserActivities("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserCheckins()
    {
        connector.getUserCheckins("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserInterest()
    {
        connector.getUserInterests("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserMusic()
    {
        connector.getUserMusic("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserBooks()
    {
        connector.getUserBooks("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserMovies()
    {
        connector.getUserMovies("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserTelevision()
    {
        connector.getUserTelevision("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserLikes()
    {
        connector.getUserLikes("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserPhotos()
    {
        connector.getUserPhotos("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }

    @Test
    public void testGetUserPhotosUploaded()
    {
        connector.getUserPhotosUploaded("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserAlbums()
    {
        connector.getUserAlbums("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserVideos()
    {
        connector.getUserVideos("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }

    @Test
    public void testGetUserVideosUploaded()
    {
        connector.getUserVideosUploaded("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserGroups()
    {
        connector.getUserGroups("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserStatuses()
    {
        connector.getUserStatuses("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserLinks()
    {
        connector.getUserLinks("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserNotes()
    {
        connector.getUserNotes("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserEvents()
    {
        connector.getUserEvents("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserInbox()
    {
        connector.getUserInbox("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserOutbox()
    {
        connector.getUserOutbox("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserUpdates()
    {
        connector.getUserUpdates("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetUserAccounts()
    {
        connector.getUserAccounts("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetVideoComments()
    {
        connector.getVideoComments("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationWall()
    {
        connector.getApplicationWall("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationTagged()
    {
        connector.getApplicationTagged("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationLinks()
    {
        connector.getApplicationLinks("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationPhotos()
    {
        connector.getApplicationPhotos("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationAlbums()
    {
        connector.getApplicationAlbums("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationStatuses()
    {
        connector.getApplicationStatuses("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationVideos()
    {
        connector.getApplicationVideos("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationNotes()
    {
        connector.getApplicationNotes("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationEvents()
    {
        connector.getApplicationEvents("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }
    
    @Test
    public void testGetApplicationInsights()
    {
        connector.getApplicationInsights("", "", "", "", "","id");
        Mockito.verify(resource).get(String.class);
    }

}