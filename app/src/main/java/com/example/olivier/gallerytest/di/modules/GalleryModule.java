package com.example.olivier.gallerytest.di.modules;

import com.example.olivier.gallerytest.yelp.YelpClient;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

@Module
public class GalleryModule {
  private String mBaseUrl;

  public GalleryModule(final String baseUrl) {
    mBaseUrl = baseUrl;
  }

  @Provides
  @Singleton OkHttpOAuthConsumer provideHttpAuth() {
    OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer("chKjzkQR0FxIYazk7Ut81Q", "U7lB_aKnfqwDhENL5e_H4qOASI0");
    consumer.setTokenWithSecret("-eAILy3Thr8Hmam7WExsR85FibBI7mV9", "Yu7RqcYN8sHhaLeTcB8mmm2vekg");
    return consumer;
  }

  @Provides
  @Singleton OkHttpClient provideHttpClient(final OkHttpOAuthConsumer consumer) {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

    return new OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(new SigningInterceptor(consumer))
        .addInterceptor(new Interceptor() {
          @Override public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (!response.isSuccessful()) {
              throw new IOException("Error: " + response.message());
            }
            return response;
          }
        })
        .build();
  }

  @Singleton
  @Provides Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(mBaseUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .client(okHttpClient)
        .build();
  }

  @Singleton
  @Provides YelpClient provideYelpClient(final Retrofit retrofit) {
    return retrofit.create(YelpClient.class);
  }
}
