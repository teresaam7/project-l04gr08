package com.st.projectst.viewer.menu;

import com.groupcdg.pitest.annotations.DoNotMutate;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Pause;
import com.st.projectst.model.menu.Win;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class WinViewerTest {
    private GUI gui;
    private Win win;
    private WinViewer winViewer;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        win = Mockito.mock(Win.class);
        winViewer = new WinViewer(win);

        when(win.getNumberOptions()).thenReturn(2);
        when(win.getOption(0)).thenReturn(" Go Back ");
        when(win.getOption(1)).thenReturn("Next Level");
    }

    @DoNotMutate
    @Test
    void testDrawObject() throws IOException, FontFormatException {
        winViewer.drawObject(gui);

        verify(gui).setBackgroundColor("#BA6156");
    }

    @DoNotMutate
    @Test
    void testDrawSelected1() throws IOException, FontFormatException {
        when(win.isSelected(0)).thenReturn(true);
        when(win.isSelected(1)).thenReturn(false);

        winViewer.drawObject(gui);
        for (int i = 0; i < win.getNumberOptions(); i++) {
            verify(gui, times(1)).drawText(
                    new Position(14, 14 + i),
                    win.getOption(i),
                    win.isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }

    @DoNotMutate
    @Test
    void testDrawSelected2() throws IOException, FontFormatException {
        when(win.isSelected(0)).thenReturn(false);
        when(win.isSelected(1)).thenReturn(true);

        winViewer.drawObject(gui);
        for (int i = 0; i < win.getNumberOptions(); i++) {
            verify(gui, times(1)).drawText(
                    new Position(14, 14 + i),
                    win.getOption(i),
                    win.isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }
}