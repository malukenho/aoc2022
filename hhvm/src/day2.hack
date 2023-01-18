<?hh // strict

$elves = "A Y\nB X\nC Z";

function lines(string $text): array<string> {
    return \explode("\n", $text);
}

<<__Memoize>>
function map_to_values(string $value): int {
    return [
        'A' => 1,
        'X' => 1,
        'B' => 2,
        'Y' => 2,
        'C' => 3,
        'Z' => 3,
    ][$value];
}

<<__Memoize>>
function process_input(string $input): array<array<int>> {
    return $input
        |> \lines($$)
        |> \array_map($x ==> \explode(" ", $x), $$)
        |> \array_map($x ==> \array_map($k ==> map_to_values($k), $x), $$)
    ;
}

function part1(string $elves): int {
    return $elves
        |> \process_input($$)
        |> \array_map(function (array<string> $match): int {
            $opponent = $match[0];
            $me       = $match[1];

            if ($me === 3 && $opponent === 1) return $me;
            if ($me === 1 && $opponent === 6) return $me + 6;
            if ($me < $opponent) return $me;
            if ($me > $opponent) return $me + 6;

            return $me + 3;
        }, $$)
        |> \array_sum($$)
    ;
}

\var_dump(\part1($elves));
