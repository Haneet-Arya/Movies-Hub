package com.haneetarya.movieshub.NetworkModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class Movies {
    private Dates dates;
    private Integer page;
    private ArrayList<Dates.Result> results = new ArrayList<>() ;
    private Integer totalPages;
    private Integer totalResults;

    public Movies() {
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Dates.Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Dates.Result> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public class Dates {
        private String maximum;
        private String minimum;

        public String getMaximum() {
            return maximum;
        }

        public void setMaximum(String maximum) {
            this.maximum = maximum;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

        public class Result {
            private Boolean adult;
            private String backdropPath;
            private List<Integer> genreIds = new ArrayList<Integer>();
            private Integer id;
            private String originalLanguage;
            private String originalTitle;
            private String overview;
            private Double popularity;
            @SerializedName("poster_path")
            private String posterPath;
            @SerializedName("release_date")
            private String releaseDate;
            private String title;
            private Boolean video;
            @SerializedName("vote_average")
            private Double voteAverage;
            @SerializedName("vote_count")
            private Integer voteCount;

            public Boolean getAdult() {
                return adult;
            }

            public void setAdult(Boolean adult) {
                this.adult = adult;
            }

            public String getBackdropPath() {
                return backdropPath;
            }

            public void setBackdropPath(String backdropPath) {
                this.backdropPath = backdropPath;
            }

            public List<Integer> getGenreIds() {
                return genreIds;
            }

            public void setGenreIds(List<Integer> genreIds) {
                this.genreIds = genreIds;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getOriginalLanguage() {
                return originalLanguage;
            }

            public void setOriginalLanguage(String originalLanguage) {
                this.originalLanguage = originalLanguage;
            }

            public String getOriginalTitle() {
                return originalTitle;
            }

            public void setOriginalTitle(String originalTitle) {
                this.originalTitle = originalTitle;
            }

            public String getOverview() {
                return overview;
            }

            public void setOverview(String overview) {
                this.overview = overview;
            }

            public Double getPopularity() {
                return popularity;
            }

            public void setPopularity(Double popularity) {
                this.popularity = popularity;
            }

            public String getPosterPath() {
                return posterPath;
            }

            public void setPosterPath(String posterPath) {
                this.posterPath = posterPath;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Boolean getVideo() {
                return video;
            }

            public void setVideo(Boolean video) {
                this.video = video;
            }

            public Double getVoteAverage() {
                return voteAverage;
            }

            public void setVoteAverage(Double voteAverage) {
                this.voteAverage = voteAverage;
            }

            public Integer getVoteCount() {
                return voteCount;
            }

            public void setVoteCount(Integer voteCount) {
                this.voteCount = voteCount;
            }
        }


    }

}

