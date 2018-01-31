package pl.bzawadka.pie.sychronization;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.bzawadka.pie.sychronization.MovieFrameRenderer.ModelFrame;
import pl.bzawadka.pie.sychronization.MovieFrameRenderer.MovieFrame;
import pl.bzawadka.pie.sychronization.MovieFrameRenderer.Reader;
import pl.bzawadka.pie.sychronization.MovieFrameRenderer.RenderEngine;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieFrameRendererTest {

    @Mock
    ModelFrame modelFrame1, modelFrame2, modelFrame3, modelFrame4;
    @Mock
    MovieFrame movieFrame1, movieFrame2, movieFrame3, movieFrame4;
    @Mock
    Reader reader;
    @Mock
    RenderEngine renderEngine;

    @Test
    public void testOneFrameIsRendered() throws ExecutionException, InterruptedException {
        when(reader.getFrames()).thenReturn(Arrays.asList(modelFrame1));
        when(renderEngine.render(modelFrame1)).thenReturn(movieFrame1);

        MovieFrameRenderer renderer = new MovieFrameRenderer(reader, renderEngine);
        List<MovieFrame> movieFrames = renderer.render();

        assertThat(movieFrames).hasSize(1);
        assertThat(movieFrames).contains(movieFrame1);
    }

    @Test
    public void testFramesAreRenderedInOrder() throws ExecutionException, InterruptedException {
        when(reader.getFrames()).thenReturn(Arrays.asList(modelFrame1, modelFrame2, modelFrame3, modelFrame4));
        when(renderEngine.render(modelFrame1)).thenReturn(movieFrame1);
        when(renderEngine.render(modelFrame2)).thenReturn(movieFrame2);
        when(renderEngine.render(modelFrame3)).thenReturn(movieFrame3);
        when(renderEngine.render(modelFrame4)).thenReturn(movieFrame4);

        MovieFrameRenderer renderer = new MovieFrameRenderer(reader, renderEngine);
        List<MovieFrame> movieFrames = renderer.render();

        assertThat(movieFrames).hasSize(4);
        assertThat(movieFrames).containsExactly(movieFrame1, movieFrame2, movieFrame3, movieFrame4);
    }
}