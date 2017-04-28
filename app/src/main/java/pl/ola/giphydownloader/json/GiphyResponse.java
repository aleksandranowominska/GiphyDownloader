package pl.ola.giphydownloader.json;

import java.util.List;

/**
 * Created by Aleksandra Kusiak on 28.04.2017.
 */

public class GiphyResponse {

    public List<GiphyData> data;
    public GiphyMeta meta;

    public class GiphyData {
        public GiphyImageTypes images;
    }

    public class GiphyImageTypes {
        public GiphyImage downsized;
    }

    public class GiphyImage {
        public String url;
    }

    public class GiphyMeta {
        public int status;
        public String msg;

    }
}
