package kg.itacademy.finalproject.service;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

public class AudioDecoderFFjpeg {
    public static void decode(String path, String outputPath) throws IOException {
        FFmpeg ffmpeg = new FFmpeg("C:\\Program Files (x86)\\FFMPEG Standalone\\bin\\ffmpeg.exe");
        FFprobe ffprobe = new FFprobe("C:\\Program Files (x86)\\FFMPEG Standalone\\bin\\ffprobe.exe");
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(path)
                .overrideOutputFiles(true)
                .addOutput(outputPath)
                .setAudioSampleRate(48_000)
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();
    }
}
