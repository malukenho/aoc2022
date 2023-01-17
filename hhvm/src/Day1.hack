<?hh // strict

$input = "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000";

<<__Memoize>>
function process_input(string $input): array<int> {
    return $input
        |> \explode("\n\n", $$)
        |> \array_map($x ==> \explode("\n", $x), $$)
        |> \array_map($x ==> \array_sum($x), $$)
    ;
}

function part1(string $input): int {
    return \max(\process_input($input));
}

function part2(string $input): int {
    $input = \process_input($input);

    \rsort($input);

    return \array_chunk($input, 3)[0]
        |> \array_sum($$);
}

\var_dump(\part1($input));
\var_dump(\part2($input));