package org.devoxx4kids.vontrappraspberrypis.common.data;

/**
 * An enumeration that represents different musical notes.
 *
 * @author Carl Jokl
 * @since 11/03/2017.
 */
public enum Note {
    // Octave 0
    REST(0, 0, 0.0f),
    A0(1, 0, 27.5f),
    BF0(2, 0, 29.135f),
    B0(3, 0, 30.868f),
    // Octave 1
    C1(4, 1, 32.703f),
    DF1(5, 1, 34.648f),
    D1(6, 1, 36.708f),
    EF1(7, 1, 38.891f),
    E1(8, 1, 41.203f),
    F1(9, 1, 43.654f),
    GF1(10, 1, 46.249f),
    G1(11, 1, 48.999f),
    AF1(12, 1, 51.913f),
    A1(13, 1, 55.0f),
    BF1(14, 1, 58.270f),
    B1(15, 1, 61.735f),
    // Octave 2
    C2(16, 2, 65.406f),
    DF2(17, 2, 69.296f),
    D2(18, 2, 73.416f),
    EF2(19, 2, 77.782f),
    E2(20, 2, 82.407f),
    F2(21, 2, 87.307f),
    GF2(22, 2, 92.499f),
    G2(23, 2, 97.999f),
    AF2(24, 2, 103.826f),
    A2(25, 2, 110.000f),
    BF2(26, 2, 116.541f),
    B2(27, 2, 123.471f),
    // Octave 3
    C3(28, 3, 130.813f),
    DF3(29, 3, 138.591f),
    D3(30, 3, 146.832f),
    EF3(31, 3, 155.563f),
    E3(32, 3, 164.814f),
    F3(33, 3, 174.614f),
    GF3(34, 3, 184.997f),
    G3(35, 3, 195.998f),
    AF3(36, 3, 207.652f),
    A3(37, 3, 220.0f),
    BF3(38, 3, 233.082f),
    B3(39, 3, 246.942f),
    // Octave 4
    C4(40, 4, 261.626f),
    DF4(41, 4, 277.183f),
    D4(42, 4, 293.665f),
    EF4(43, 4, 311.127f),
    E4(44, 4, 329.628f),
    F4(45, 4, 349.228f),
    GF4(46, 4, 369.994f),
    G4(47, 4, 391.955f),
    AF4(48, 4, 415.305f),
    A4(49, 4, 440.0f),
    BF4(50, 4, 466.164f),
    B4(51, 4, 493.883f),
    // Octave 5
    C5(52, 5, 523.251f),
    DF5(53, 5, 554.365f),
    D5(54, 5, 587.33f),
    EF5(55, 5, 622.254f),
    E5(56, 5, 659.255f),
    F5(57, 5, 698.456f),
    GF5(58, 5, 739.989f),
    G5(59, 5, 783.991f),
    AF5(60, 5, 830.609f),
    A5(61, 5, 880.000f),
    BF5(62, 5, 932.328f),
    B5(63, 5, 987.767f),
    // Octave 6
    C6(64, 6, 1046.502f),
    DF6(65, 6, 1108.731f),
    D6(66, 6, 1174.659f),
    EF6(67, 6, 1244.508f),
    E6(68, 6, 1318.510f),
    F6(69, 6, 1396.913f),
    GF6(70, 6, 1479.978f),
    G6(71, 6, 1567.982f),
    AF6(72, 6, 1661.219f),
    A6(73, 6, 1760.0f),
    BF6(74, 6, 1864.655f),
    B6(75, 6, 1975.533f),
    // Octave 7
    C7(76, 7, 2093.005f),
    DF7(77, 7, 2217.461f),
    D7(78, 7, 2349.318f),
    EF7(79, 7, 2489.016f),
    E7(80, 7, 2637.02f),
    F7(81, 7, 2793.826f),
    GF7(82, 7, 2959.955f),
    G7(83, 7, 3135.963f),
    AF7(84, 7, 3322.438f),
    A7(85, 7, 3520.0f),
    BF7(86, 7, 3729.310f),
    B7(87, 7, 3951.066f),
    // Octave 8
    C8(88, 8, 4186.009f),
    DF8(89, 8, 4434.922f),
    D8(90, 8, 4698.636f),
    EF8(91, 8, 4978.032f),
    E8(92, 8, 5274.041f),
    F8(93, 8, 5587.652f),
    GF8(94, 8, 5919.911f),
    G8(95, 8, 6271.927f),
    AF8(96, 8, 6644.875f),
    A8(97, 8, 7040.0f);

    public static Note fromId(final int id) {
        final Note[] notes = values();
        if (id >= 0 && id < notes.length) {
            return notes[id];
        }
        else {
            return REST;
        }
    }

    private final int id;
    private final int octave;
    private final float frequencyHertz;

    private Note(final int id, final int octave, final float frequencyHertz) {
        this.id = id;
        this.octave = octave;
        this.frequencyHertz = frequencyHertz;
    }

    public int getId() {
        return id;
    }

    public int getOctave() {
        return octave;
    }

    public float getFrequencyHertz() {
        return frequencyHertz;
    }
}
