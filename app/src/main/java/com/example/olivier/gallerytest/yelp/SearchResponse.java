package com.example.olivier.gallerytest.yelp;

import android.graphics.Region;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import java.io.Serializable;
import java.util.ArrayList;

@AutoValue
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = AutoValue_SearchResponse.Builder.class)
public abstract class SearchResponse implements Serializable {

  public abstract ArrayList<Business> businesses();

  public abstract Integer total();

  @AutoValue.Builder
  @JsonPOJOBuilder(withPrefix = "")
  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
  public abstract static class Builder {

    public abstract Builder businesses(ArrayList<Business> businesses);

    public abstract Builder total(Integer total);

    public abstract SearchResponse build();
  }

  public static Builder builder() {
    return new AutoValue_SearchResponse.Builder();
  }
}