package pl.bzawadka.pie.sychronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Concurrent movie frames renderer.
 * Preserves the order of the frames.
 */
public class MovieFrameRenderer {
    interface ModelFrame {
    }

    interface MovieFrame {
    }

    interface Reader {
        List<ModelFrame> getFrames();
    }

    interface RenderEngine {
        /** time/resources expensive operation */
        MovieFrame render(ModelFrame modelFrame);
    }

    private final int THREAD_POOL_SIZE = 10;
    private final Reader reader;
    private final RenderEngine renderEngine;

    public MovieFrameRenderer(Reader reader, RenderEngine renderEngine) {
        this.reader = reader;
        this.renderEngine = renderEngine;
    }

    public List<MovieFrame> render() throws ExecutionException, InterruptedException {
        List<ModelFrame> src = reader.getFrames();
        List<MovieFrame> dest = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future<MovieFrame>> renderingFutures = new ArrayList<>();

        for (ModelFrame modelFrame : src) {
            Future<MovieFrame> future = executorService.submit(() -> renderEngine.render(modelFrame));
            renderingFutures.add(future);
        }

        for (Future<MovieFrame> future : renderingFutures) {
            // wait for completion of rendering; Futures collection maintain original order of ModelFrames
            dest.add(future.get());
        }
        executorService.shutdown();
        return dest;
    }
}
